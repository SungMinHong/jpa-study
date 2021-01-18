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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(100);
            member.changeTeam(team);
            em.persist(member);
            
            em.flush();
            em.clear();
            System.out.println("=============================");
            
            String query = "SELECT m FROM Member AS m INNER JOIN m.team t";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();
            System.out.println("=============================");
            System.out.println(result.get(0).getTeam().getName());
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
