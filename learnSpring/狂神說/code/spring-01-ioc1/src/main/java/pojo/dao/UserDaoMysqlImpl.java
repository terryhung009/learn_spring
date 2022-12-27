package pojo.dao;

public class UserDaoMysqlImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("MySQL獲取用戶數據");
    }
}
