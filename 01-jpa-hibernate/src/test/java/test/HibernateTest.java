package test;

import com.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HibernateTest {

    //Session工廠  Session:數據庫對話 代碼持久化操作數據的一個橋樑
    //可以理解成mybatis的sql session工廠
    private SessionFactory sf;

    @Before
    public void init(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/hibernate.cfg.xml")
                .build() ;

        //根據服務註冊類創建一個元數據資源集，同時構建元數據並生成應用一般唯一的session工廠

        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    @Test
    public void test(){
        //創建session
        Session sess = sf.openSession();
        //開始事務
        Transaction transaction = sess.beginTransaction();
        //創建消息隊列
        Customer customer = new Customer();
        customer.setCustName("張三");

        //保存消息
        sess.save(customer);
        //提交事務
        transaction.commit();
        //關閉session
        sess.close();
        sf.close();

    }
    @Test
    public void testCreate(){
        //session進行持久化操作
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustName("徐庶");

            session.save(customer);

            transaction.commit();


        }




    }
    @Test
    public void testRead(){
        //session進行持久化操作
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();

//            Customer customer = new Customer();
//            customer.setCustName("徐庶");
//
//            session.save(customer);
            Customer customer = session.find(Customer.class, 1L);
//            Customer customer = session.load(Customer.class, 1L);
            System.out.println("====================");
            System.out.println(customer);

            transaction.commit();


        }




    }
    @Test
    public void testRead_lazy(){
        //session進行持久化操作
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();

//            Customer customer = new Customer();
//            customer.setCustName("徐庶");
//
//            session.save(customer);
//            Customer customer = session.find(Customer.class, 1L);
            Customer customer = session.load(Customer.class, 1L);//使用懶加載方式
            System.out.println("====================");
            System.out.println(customer);

            transaction.commit();


        }
    }

    @Test
    public void testUpdate(){
        //session進行持久化操作
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustId(1L);
            customer.setCustName("徐庶");
            //插入session.save();
            //更新session.update();
            session.saveOrUpdate(customer);

            transaction.commit();


        }
    }

    @Test
    public void testDelete(){
        //session進行持久化操作
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustId(2L);
//            customer.setCustName("徐庶");
            //插入session.save();
            //更新session.update();
            session.remove(customer);

            transaction.commit();


        }
    }
    @Test
    public void testReadByHQL(){
        //session進行持久化操作
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
//            查詢全部
//            String hql = " FROM Customer";
//            List<Customer> resultList = session.createQuery(hql, Customer.class)
//
//                    .getResultList();
            //使用where
            String hql = " FROM Customer where custId=:id";//具名參數


            List<Customer> resultList = session.createQuery(hql, Customer.class)
                    .setParameter("id",1L)
                    .getResultList();

            System.out.println(resultList);

//            Customer customer = new Customer();
//            customer.setCustId(2L);
//            customer.setCustName("徐庶");
            //插入session.save();
            //更新session.update();
//            session.remove(customer);

            transaction.commit();


        }
    }



}
