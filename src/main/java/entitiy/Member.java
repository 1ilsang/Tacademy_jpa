package main.java.entitiy;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    // Id 는 PK 를 의미한다.
    // GeneratedValue 는 DB 에 생성을 맡기는거. auto 등
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // DB에는 USERNAME 으로 생성하게 된다. nullable = false 는 not null 이다.
    // 글자 제한을 주고 싶으면 length
    @Column(name = "USERNAME", nullable = false, length = 20)
    private String name;

    private int age;

    @Temporal(TemporalType.DATE)
    private Date regDate;

    // 반드시 스트링으로 진행.
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    // 너무 길 경우 바이너리로 밀어넣을때 사용
    @Lob
    private byte[] bytes;

//    // 참조 대신 외래키를 그대로 넣은 것.
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // 단방향 매핑
//    @ManyToOne
    // 지연 로딩
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    // 양방향 매핑
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
