package com.example.javaweblab3.service;

import com.example.javaweblab3.dao.UserDao;
import com.example.javaweblab3.dao.daoimp.UserDaoImp;
import com.example.javaweblab3.entity.User;
import com.example.javaweblab3.factory.DAOFactory;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = DAOFactory.getUserDaoInstance();
    }

    public User userLogin(String username, String password) throws Exception {
        return userDao.userLogin(username, password);
    }
}
