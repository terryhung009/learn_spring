
import pojo.dao.UserDaoOracleImpl;
import pojo.service.UserServiceImpl;

public class MyTest1 {

    public static void main(String[] args) {

        //用戶實際調用的是業務層，dao層他們不需要接觸
        UserServiceImpl userService = new UserServiceImpl();

        userService.setUserDao(new UserDaoOracleImpl());

        userService.getUser();
    }
}
