package danny.demo01;

public class Proxy implements Rent{

    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
        host.rent();
        hetong();
        fare();
    }

    //看房
    public void seeHouse(){
        System.out.println("仲介帶你看房");
    }

    //收仲介費
    public void fare(){
        System.out.println("收仲介費");
    }
    //簽合約
    public void hetong(){
        System.out.println("簽合約");
    }

}
