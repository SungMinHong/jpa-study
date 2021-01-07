package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String username;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }
}
