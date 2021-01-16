import jpql.Member;
import jpql.MemberDTO;

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

            MemberDTO singleResult = em.createQuery("SELECT new jpql.MemberDTO(m.username, m.age)  FROM Member AS m", MemberDTO.class)
                    .getSingleResult();
            
            System.out.println("username: " + singleResult.getUsername());
            System.out.println("age: " + singleResult.getAge());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
