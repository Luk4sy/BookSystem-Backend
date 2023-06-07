package com.example.javaweblab3.dao.daoimp;

import com.example.javaweblab3.dao.BookDao;
import com.example.javaweblab3.entity.Book;
import com.example.javaweblab3.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImp implements BookDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public List<Book> getAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        conn = DruidUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            book.setShelfNumber(rs.getString("shelf_number"));
            book.setStatus(rs.getString("status"));
            books.add(book);
        }

        DruidUtil.close(conn, pstmt, rs);
        return books;
    }

    @Override
    public void updateBookStatus(int bookId, String status) throws Exception {
        String sql = "UPDATE book SET status = ? WHERE id = ?";
        conn = DruidUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, status);
        pstmt.setInt(2, bookId);
        pstmt.executeUpdate();
        DruidUtil.close(conn, pstmt);
    }

    @Override
    public void addBook(Book book) throws Exception {
        String sql = "INSERT INTO book (title, author, isbn, shelf_number, status) VALUES (?, ?, ?, ?, ?)";
        conn = DruidUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getTitle());
        pstmt.setString(2, book.getAuthor());
        pstmt.setString(3, book.getIsbn());
        pstmt.setString(4, book.getShelfNumber());
        pstmt.setString(5, book.getStatus());
        pstmt.executeUpdate();
        DruidUtil.close(conn, pstmt);
    }
}
