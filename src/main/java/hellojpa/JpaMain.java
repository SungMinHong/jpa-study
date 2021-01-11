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
            //팀 저장
            System.out.println("팀 저장");
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            
            //회원 저장 
            System.out.println("회원 저장");
            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            // 양방향 맵핑이기 때문에 team내 member 리스트에도 member를 넣어주는 습관을 갖자
            // 이는 순수 객체 상태를 고려했기 때문
            team.getMembers().add(member);

            em.flush(); // DB에 쿼리 반영
            em.clear(); // 1차 캐시(영속성 컨텍스트) 초기화
            
            System.out.println("멤버 찾기");
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            System.out.println("team내 멤버 이름 출력");
            members.stream()
                    .map(Member::getUsername)
                    .forEach((name) -> System.out.println("memberName: " + name));
            
            System.out.println("트랜잭션 커밋");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
