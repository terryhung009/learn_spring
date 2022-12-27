package danny.demo01;

public class Client {
    public static void main(String[] args) {
//        Host host=new Host();
//        host.rent();
//無法直接租房，要透過仲介

        //房東要租房子
        Host host = new Host();
        //代理，中介幫房東租房子，但是呢，代理一般會有一些附屬操作
        Proxy proxy = new Proxy(host);

        //你不用找房東，直接找仲介即可
        proxy.rent();
    }
}
