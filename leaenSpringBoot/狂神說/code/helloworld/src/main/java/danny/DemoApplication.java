package danny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//本身就是Spring 的一個組件

//程序的主入口
//@SpringBootApplication: 標註這個類是一個springboot的應用
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//將springboot應用啟動
		SpringApplication.run(DemoApplication.class, args);
	}

}
