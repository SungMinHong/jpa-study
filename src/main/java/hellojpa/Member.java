package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    
    @Column
    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    // 연관관계 편의 메소드를 생성하자
    public void setTeam(Team team) {
        this.team = team;
        // 양방향 맵핑이기 때문에 team내 member 리스트에도 member를 넣어주는 습관을 갖자
        // 이는 순수 객체 상태를 고려했기 때문
        team.getMembers().add(this);
    }
}
