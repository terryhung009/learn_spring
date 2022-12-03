import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Hello;

import java.util.logging.Handler;

public class MyTest {

    public static void main(String[] args) {
        //獲取spring的上下文對象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我們的對象現在都在spring的管理了，我們要使用，直接去裡面取出來就可以
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());



    }
}
