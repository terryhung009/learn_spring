import danny.service.UserService;
import danny.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //動態代理的是接口,注意點
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();

    }
}
