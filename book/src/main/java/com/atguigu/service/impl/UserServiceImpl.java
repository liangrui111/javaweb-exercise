package com.atguigu.service.impl;

import com.atguigu.dao.impl.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;

public class UserServiceImpl implements UserService{

    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUserName(String username) {
        if(userDao.queryUserByUsername(username)==null){
            //等于null 表示没查到
            return false;
        }
        return true;
    }
}
