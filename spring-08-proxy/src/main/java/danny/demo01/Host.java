package danny.demo01;

//房東
public class Host implements Rent{
    @Override
    public void rent() {
        System.out.println("房東要出租房子啦!");
    }
}
