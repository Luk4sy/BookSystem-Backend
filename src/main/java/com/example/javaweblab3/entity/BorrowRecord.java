package com.example.javaweblab3.entity;

import java.util.Date;

public class BorrowRecord {
    private int id;
    private User user;
    private Book book;
    private Date borrowDate;
    private Date returnDate;
    private Date dueDate;

    public BorrowRecord() {
    }

    public BorrowRecord(int id, User user, Book book, Date borrowDate, Date returnDate, Date dueDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BorrowRecord(User user, Book book, Date borrowDate, Date returnDate, Date dueDate) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
    }


}
