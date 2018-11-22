package main.java.hellojpa;

import main.java.entitiy.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String args[]) {
        // 서버 실행시 한 번만 실행해야함.
        // /META-INF/Persistence.xml 에서 이름이 hello 인 persistence-unit 을 찾아서 엔티티 매니저 팩토리를 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        System.out.println("hello");

        // 고객 요청시 얘를 호출
        EntityManager em = emf.createEntityManager();

        // JPA 의 모든 요청은 트랜잭션을 걸어줘야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(100L);
            member.setName("1ilsang");

            // 영구 저장
            em.persist(member);

            // 커밋 완료와 매니저를 반드시 회수해야한다.
            tx.commit();
        } catch (Exception e) {
            // 커밋 실패시 트랜잭션을 롤백한다.
            tx.rollback();
        } finally {
            // 커밋 성공시 매니저 회수.
            em.close();
        }

        emf.close();
    }
}
