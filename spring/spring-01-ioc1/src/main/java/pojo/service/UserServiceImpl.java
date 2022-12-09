package pojo.service;

import pojo.dao.UserDao;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

//    private UserDao userDao = new UserDaoMysqlImpl();

    //利用set進行動態實現值的注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
