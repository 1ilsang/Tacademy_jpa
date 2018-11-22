package main.java.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    private Long id;
    private String name;

    // 단방향을 양방향으로 바꾸는 것이다.
    // mappedBy 는 연관관계의 주인을 의미. team 이 주인이 되는 것.
    // 주인이 아니면 조회만 가능하다. 주인은 mappedBy 속성 안씀.
    // 컬럼이 생기지 않는다. (객체와 컬럼의 차이를 해결)
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
