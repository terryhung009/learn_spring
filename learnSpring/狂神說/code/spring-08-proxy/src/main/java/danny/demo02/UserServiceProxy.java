package danny.demo02;

public class UserServiceProxy implements UserService{

    private UserServiceImpl userService;

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public UserServiceProxy(){

    }

    @Override
    public void add() {
        //       System.out.println("使用了add方法");
        log("add");
        userService.add();

    }

    @Override
    public void delete() {
//        System.out.println("使用了delete方法");
        log("delete");
        userService.delete();
    }

    @Override
    public void update() {
//        System.out.println("使用了update方法");
        log("update");
        userService.update();
    }

    @Override
    public void query() {
        log("query");
        System.out.println("使用了query方法");
        userService.query();
    }
    //日誌方法
    public void log(String msg){
        System.out.println("[Debug] 使用了" + msg + "方法");
    }
}
