import org.example.pojo.User;
import org.example.pojo.UserT;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest1 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

//        User user = (User) ac.getBean("user");
//
//        user.show();

//        user.toString();

//        User user = (User) ac.getBean("aaa");

//        user.show();

        UserT user = (UserT) ac.getBean("user2");

        user.show();


    }
}
