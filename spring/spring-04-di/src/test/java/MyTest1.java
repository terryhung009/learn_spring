import danny.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest1 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = (Student) context.getBean("student");

        System.out.println(student.toString());

        /*
         Student{name='danny',
         address=Address{address='null'},
         books=[紅樓夢, 西遊記, 三國演義],
         hobbys=[聽歌, 敲代碼, 看電影],
         card={身分證=11111, 銀行卡=22222},
         games=[LOL, COC, BOB],
         info={性別=男, 學號=111111},
         wife='null'}
         */


    }
}
