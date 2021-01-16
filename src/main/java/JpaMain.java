import jpql.Member;

import javax.persistence.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("홍");
            member.setAge(99);
            em.persist(member);

            Member singleResult = em.createQuery("SELECT m FROM Member AS m WHERE m.username = :username", Member.class)
                    .setParameter("username", "홍")
                    .getSingleResult();
            
            System.out.println(singleResult.getUsername());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
