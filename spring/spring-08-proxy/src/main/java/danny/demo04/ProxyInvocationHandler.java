package danny.demo04;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


//等會我們會用這個類，自動生成代理類
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到代理類
    public Object getProxy(){
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
                );
    }

    //處理代理實例，並返回結果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log(method.getName());

        //動態代理的本質，就是使用反射機制實現
        Object result = method.invoke(target, args);

        return result;
    }

    public void log(String msg){
        System.out.println("執行了 " + msg +" 方法");
    }


}
