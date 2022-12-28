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
                .setParameter("name", "太極張三豐")
                .setParameter("id", 4L)
                .executeUpdate();


        transaction.commit();

    }

    //修改，使用SQL
    @Test
    public void testUpdate_SQL() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        String sql = "UPDATE `cst_customer` set cust_name=:name where cust_id=:id";
        entityManager.createNativeQuery(sql)
                .setParameter("name", "明教教主張無忌")
                .setParameter("id", 3L)
                .executeUpdate();


        transaction.commit();

    }

    //刪除
    @Test
    public void testDelete() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        /**會報錯!數據必須要持久化狀態的
         * 有游離狀態(detached)
         * 持久化狀態(persistence)
         */
//        Customer customer = new Customer();
//        customer.setCustId(5L);
//
//
//        entityManager.remove(customer);
//        transaction.commit();

        /**改成先查詢，再刪除
         *
         */
        Customer customer = entityManager.find(Customer.class, 5L);


        entityManager.remove(customer);
        transaction.commit();


    }

    //狀態01
    @Test
    public void testStatus01() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        Customer customer = new Customer();//臨時狀態(瞬時狀態)
        customer.setCustId(6L);//游離狀態
        customer = entityManager.find(Customer.class, 5L);//持久狀態
        entityManager.remove(customer);//刪除狀態(銷毀狀態)


        transaction.commit();


    }

    //狀態02
    @Test
    public void testStatus02() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        Customer customer = entityManager.find(Customer.class, 6L);//持久狀態
        //持久狀態進行了修改就會同步數據庫
        customer.setCustName("徐庶大帥哥");//設定資料


        transaction.commit();//就會直接更新數據庫資料


    }

    //狀態03
    @Test
    public void testStatus03() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        Customer customer = entityManager.find(Customer.class, 6L);//持久狀態
        //持久狀態進行了修改就會同步數據庫
        entityManager.remove(customer);//變成刪除狀態

        entityManager.persist(customer);//變成受控狀態

        //這次查詢不會執行刪除及插入，只會執行查詢data
        transaction.commit();


    }

    //緩存
    @Test
    public void testCache01() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        /**只會查詢一次
         *
         */
        Customer customer = entityManager.find(Customer.class, 1L);
        Customer customer2 = entityManager.find(Customer.class, 1L);


        transaction.commit();


    }

    //緩存02
    @Test
    public void testCache02() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        Customer customer = entityManager.find(Customer.class, 1L);
        Customer customer2 = entityManager.find(Customer.class, 1L);


        transaction.commit();

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        EntityTransaction transaction2 = entityManager.getTransaction();
        transaction2.begin();

        Customer customer3 = entityManager.find(Customer.class, 1L);

        /**
         * 會查詢2次
         */
        transaction2.commit();

    }

}
