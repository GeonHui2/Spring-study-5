package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");
        //엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유

        EntityManager em = enf.createEntityManager();
        //엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 한다).

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address("city","street", "10000"));
            member.setWorkPeriod(new Period());

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        enf.close();
    }
}
