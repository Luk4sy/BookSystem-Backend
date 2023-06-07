package com.example.javaweblab3.dao;

import com.example.javaweblab3.entity.Book;

import java.util.List;

public interface BookDao {
    /**
     * 获取所有书籍信息
     *
     * @return List<Book> 用List存放的书籍信息
     * @throws Exception 抛出异常
     */
    public List<Book> getAllBooks() throws Exception;

    /**
     * @param bookId 书籍id
     * @param status 书的状态
     * @throws Exception 抛出异常
     */
    void updateBookStatus(int bookId, String status) throws Exception;

    /**
     *
     * @param book 书籍
     * @throws Exception 抛出异常
     */
    void addBook(Book book) throws Exception;
}
