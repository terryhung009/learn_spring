package danny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//自動裝配：原理!
//接口(endpoint):http://localhost:9090/hello
@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping("/hello")
    public String hello(){
        //調用業務，接收前端參數!
        return "Hello,World";
    }




}
