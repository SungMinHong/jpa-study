import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(100);
            member1.changeTeam(teamA);
            em.persist(member1);
            
            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(33);
            member2.changeTeam(teamA);
            em.persist(member2);
            
            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(22);
            member3.changeTeam(teamB);
            em.persist(member3);
            
            em.flush();
            em.clear();
            System.out.println("=============================");
            
            String query = "SELECT m FROM Member AS m JOIN FETCH m.team";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();
            
            System.out.println("=============================");
            for (Member m : result) {
                System.out.println("member: " + m.getUsername() + " " + m.getTeam().getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
