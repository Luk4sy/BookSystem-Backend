package com.example.javaweblab3.entity;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String shelfNumber;
    private String status;

    public Book(int id, String title, String author, String isbn, String shelfNumber, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.shelfNumber = shelfNumber;
        this.status = status;
    }

    public Book(String title, String author, String isbn, String shelfNumber, String status) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.shelfNumber = shelfNumber;
        this.status = status;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(String shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
