package test;

import com.example.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HibernateJpaTest {

    EntityManagerFactory entityManagerFactory;

    @Before
    public void before() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernateJPA");
    }

    @Test
    public void testCreate() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setCustName("張三");

        entityManager.persist(customer);
        transaction.commit();


    }

    //立即查詢
    @Test
    public void testRead() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

//        Customer customer= new Customer();
//        customer.setCustName("張三");
//        entityManager.persist(customer);

        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.println("============================");
        System.out.println(customer);


        transaction.commit();
    }

    //延遲查詢(lazy query)
    @Test
    public void testLazyRead() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        Customer customer = entityManager.getReference(Customer.class, 1L);
        System.out.println("============================");
        System.out.println(customer);


        transaction.commit();
    }

    //修改
    @Test
    public void testUpdate() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
//        customer.setCustId(5L);//若不輸入id，則進行插入動作
        customer.setCustName("太極張三豐");

        /**如果指定了主鍵id:
         * 更新:1.先查詢，看是否有變化
         *如果有變化->更新     如果沒變化->就不更新
         *
         * 如果沒有指定主鍵
         * 執行插入
         *
         */

        entityManager.merge(customer);
        transaction.commit();


    }

    //修改，使用JPQL
    @Test
    public void testUpdate_JPQL() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        String jqpl = "UPDATE Customer set custName=:name where custId=:id";
        entityManager.createQuery(jqpl)
                .setParameter("name","太極張三豐")
                .setParameter("id",4L)
                .executeUpdate();


        transaction.commit();

    }
    //修改，使用SQL
    @Test
    public void testUpdate_SQL() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        String sql = "UPDATE Customer set custName=:name where custId=:id";
        entityManager.createNativeQuery(sql)
                .setParameter("name","太極張三豐")
                .setParameter("id",4L)
                .executeUpdate();


        transaction.commit();

    }

}
