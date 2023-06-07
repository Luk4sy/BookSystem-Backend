package com.example.javaweblab3.service;

import com.example.javaweblab3.dao.BookDao;
import com.example.javaweblab3.entity.Book;
import com.example.javaweblab3.factory.DAOFactory;

import java.util.List;

public class BookService {
    private BookDao bookDao;

    public BookService() {
        this.bookDao = DAOFactory.getBookDaoInstance();
    }

    public List<Book> getAllBooks() throws Exception {
        return bookDao.getAllBooks();
    }

    public void updateBookStatus(int bookId, String status) throws Exception {
        bookDao.updateBookStatus(bookId, status);
    }

    public void addBook(Book book) throws Exception {
        bookDao.addBook(book);
    }
}
