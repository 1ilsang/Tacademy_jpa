package main.java.hellojpa;

import main.java.entitiy.Member;
import main.java.entitiy.MemberType;
import main.java.entitiy.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String args[]) {
        // 팩토리는 서버 실행시 한 번만 실행해야함. 하나만 생성해 앱 전체에서 공유함.
        // /META-INF/Persistence.xml 에서 이름이 hello 인 persistence-unit 을 찾아서 엔티티 매니저 팩토리를 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        System.out.println("hello");

        // 매니저는 쓰레드간 공유하면 안된다. (사용하고 버려야 함)
        EntityManager em = emf.createEntityManager();

        // JPA 의 모든 요청은 트랜잭션을 걸어줘야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 외래키 부분.
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
//            member.setId(100L);
            member.setName("1ilsang");
            member.setAge(26);
            member.setMemberType(MemberType.ADMIN);
            member.setRegDate(new java.util.Date());
//            member.setTeamId(team.getId());

            // 양방향의 이해는 아래 두 줄의 차이점을 아는 것이 중요하다.
            member.setTeam(team);
//            team.getMembers().add(member);

            Member member2 = new Member();
            member2.setName("2ilsang");
            member2.setTeam(team);

            // 영구 저장
            em.persist(member);
            em.persist(member2);

            // 이력 깔끔하게 캐싱 등 제거
            em.flush();
            em.clear();

            // 조회, 단방향 매핑
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            int memberSize = findTeam.getMembers().size();

            System.out.println("FindTeam.getName: " + findTeam.getName() + ", Membersize: " + memberSize);

            // 커밋 완료와 매니저를 반드시 회수해야한다.
            tx.commit();

        } catch (Exception e) {
            // 커밋 실패시 트랜잭션을 롤백한다.
            tx.rollback();
        } finally {
            // 커밋 성공시 매니저 회수.
            em.close();
        } // end try

        emf.close();
    }
}
