package danny.demo04;

import danny.demo02.UserService;
import danny.demo02.UserServiceImpl;

public class Client {
    public static void main(String[] args) {
        //真實角色
        UserServiceImpl userService = new UserServiceImpl();

        //代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();

        //設置要代理的對象
        pih.setTarget(userService);
        //動態生成代理類
        UserService proxy = (UserService) pih.getProxy();

        proxy.add();
        proxy.delete();

    }
}
