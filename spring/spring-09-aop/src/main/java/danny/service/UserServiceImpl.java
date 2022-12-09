package danny.service;

public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加了一個用戶");
    }

    @Override
    public void delete() {
        System.out.println("刪除了一個用戶");
    }

    @Override
    public void update() {
        System.out.println("修改了一個用戶");
    }

    @Override
    public void query() {
        System.out.println("查詢了一個用戶");
    }
}
