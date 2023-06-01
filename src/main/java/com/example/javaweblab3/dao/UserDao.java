package com.example.javaweblab3.dao;

import com.example.javaweblab3.entity.User;
import com.example.javaweblab3.util.DruidUtil;

import java.sql.Connection;

public interface UserDao {
    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回一个User对象
     * @throws Exception 抛出异常
     */
    //登录验证
    public User userLogin(String username, String password) throws Exception;
}
