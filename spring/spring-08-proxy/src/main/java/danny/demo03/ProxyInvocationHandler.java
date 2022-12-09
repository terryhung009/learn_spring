package danny.demo03;






import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


//等會我們會用這個類，自動生成代理類
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    //生成得到代理類
    public Object getProxy(){
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                rent.getClass().getInterfaces(),
                this
                );
    }

    //處理代理實例，並返回結果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        seeHouse();
        //動態代理的本質，就是使用反射機制實現
        Object result = method.invoke(rent, args);
        fare();
        return result;
    }

    public void seeHouse(){
        System.out.println("仲介帶看房子");
    }
    public void fare(){
        System.out.println("仲介收仲介費");
    }


}
