import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.service.UserServiceImpl;

public class MyTest2 {
    public static void main(String[] args) {
        //獲取ApplicationContext
        ApplicationContext beans = new ClassPathXmlApplicationContext("beans.xml");

        UserServiceImpl userServiceImpl = (UserServiceImpl) beans.getBean("UserServiceImpl");

        userServiceImpl.getUser();

    }
}
