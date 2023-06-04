package com.example.javaweblab3.factory;

import com.example.javaweblab3.dao.BookDao;
import com.example.javaweblab3.dao.UserDao;
import com.example.javaweblab3.dao.daoimp.BookDaoImp;
import com.example.javaweblab3.dao.daoimp.UserDaoImp;

public class DAOFactory {
    public static BookDao getBookDaoInstance() {
        return new BookDaoImp();
    }

    public static UserDao getUserDaoInstance() {
        return new UserDaoImp();
    }
}
