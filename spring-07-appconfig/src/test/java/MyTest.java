import danny.User;
import danny.config.MyConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        //如果完全使用了配置類方式去做，我們就只能通過 AnnotationConfig上下文來獲取容器，通過配置類的class對象加載。
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user.getName());

    }



}
