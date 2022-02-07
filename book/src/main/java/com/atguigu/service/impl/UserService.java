package com.atguigu.service.impl;

import com.atguigu.pojo.User;

public interface UserService {
    /**
     * 注册用户
     */
    public void registerUser(User user);

    /**
     * 登录
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可使用
     */
    public boolean existUserName(String username);
}
