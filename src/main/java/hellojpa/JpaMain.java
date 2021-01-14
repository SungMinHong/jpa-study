package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team newTeam = new Team();
            newTeam.setName("1팀");
            em.persist(newTeam);
            
            Member newMember = new Member();
            newMember.setUsername("홍길동");
            newMember.changeTeam(newTeam);
            em.persist(newMember);
            
            em.flush();
            em.clear();
            
            Member member = em.find(Member.class, newMember.getId());
            System.out.println("team class: " + member.getTeam().getClass());
            
            System.out.println("team name: " + member.getTeam().getName());
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
