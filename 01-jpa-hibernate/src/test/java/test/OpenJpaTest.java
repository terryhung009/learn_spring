package test;

import com.example.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OpenJpaTest {

    EntityManagerFactory entityManagerFactory;

    @Before
    public void before() {
        entityManagerFactory = Persistence.createEntityManagerFactory("openJpa");
    }

    @Test
    public void testCreate(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer= new Customer();
        customer.setCustName("張三");

        entityManager.persist(customer);



        transaction.commit();
    }
    @Test
    public void testRead(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

//        Customer customer= new Customer();
//        customer.setCustName("張三");
//        entityManager.persist(customer);

        Customer customer = entityManager.find(Customer.class,1L);
        System.out.println("============================");
        System.out.println(customer);


        transaction.commit();
    }


}
