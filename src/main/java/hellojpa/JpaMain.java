package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("홍길동");
            member.setHomeAddress(new Address("city", "street", "zipcode"));
            
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("햄버거");

            member.getAddressHistory().add(new Address("old1", "street", "zipcode"));
            member.getAddressHistory().add(new Address("old2", "street", "zipcode"));
            
            em.persist(member);
            
            em.flush();
            em.clear();

            System.out.println("==============start==============");
            Member findMember = em.find(Member.class, member.getId());  // 값 타입 컬렉션은 지연로딩임
//            List<Address> addressHistory = findMember.getAddressHistory();
            
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }

//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
            
            findMember.getAddressHistory().remove(new Address("old1", "street", "zipcode"));
            findMember.getAddressHistory().add(new Address("newCity1", "street", "zipcode"));
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
