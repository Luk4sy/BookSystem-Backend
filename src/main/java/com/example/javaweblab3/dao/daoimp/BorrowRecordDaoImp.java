package com.example.javaweblab3.dao.daoimp;

import com.example.javaweblab3.dao.BorrowRecordDao;
import com.example.javaweblab3.entity.Book;
import com.example.javaweblab3.entity.BorrowRecord;
import com.example.javaweblab3.entity.User;
import com.example.javaweblab3.factory.DAOFactory;
import com.example.javaweblab3.util.DruidUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowRecordDaoImp implements BorrowRecordDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    @Override

    public List<BorrowRecord> getAllRecords() throws Exception {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM borrowRecord INNER JOIN user ON borrowRecord.user_id = user.id INNER JOIN book ON borrowRecord.book_id = book.id";
        conn = DruidUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            User user = new User();
            Book book = new Book();
            int id = rs.getInt("id");

            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            book.setId(rs.getInt("book_id"));
            book.setTitle((rs.getString("title")));
            book.setShelfNumber(rs.getString("shelf_number"));
            Date borrowDate = rs.getDate("borrow_date");
            Date returnDate = rs.getDate("return_date");
            Date dueDate = rs.getDate("due_date");
            System.out.println(borrowDate);
            System.out.println(returnDate);
            System.out.println(dueDate);

            BorrowRecord record = new BorrowRecord(id, user, book, borrowDate, returnDate, dueDate);
            records.add(record);

        }

        DruidUtil.close(conn, pstmt, rs);
        return records;
    }

    @Override
    public List<BorrowRecord> getRecordByUserId(int userId) throws Exception {
        List<BorrowRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM borrowRecord INNER JOIN user ON borrowRecord.user_id = user.id INNER JOIN book ON borrowRecord.book_id = book.id WHERE user.id = ?";
        conn = DruidUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userId);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            User user = new User();
            Book book = new Book();
            int id = rs.getInt("id");

            user.setUsername(rs.getString("username"));
            book.setTitle(rs.getString("title"));
            book.setShelfNumber(rs.getString("shelf_number"));
            Date borrowDate = rs.getDate("borrow_date");
            Date returnDate = rs.getDate("return_date");
            Date dueDate = rs.getDate("due_date");

            BorrowRecord record = new BorrowRecord(id, user, book, borrowDate, returnDate, dueDate);
            records.add(record);
        }

        DruidUtil.close(conn, pstmt, rs);
        return records;
    }

    @Override
    public boolean returnBook(int recordId) throws Exception {
        String sql = "UPDATE borrowRecord SET due_date = ? WHERE id = ?";
        String sql2 = "SELECT book_id FROM borrowRecord WHERE id = ?";
        String sql3 = "UPDATE book SET status = '可借阅' WHERE id = ?";
        conn = DruidUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        // 将 due_date 设置为当前日期
        pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
        pstmt.setInt(2, recordId);

        int affectedRows = pstmt.executeUpdate();

        pstmt = conn.prepareStatement(sql2);
        pstmt.setInt(1, recordId);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            int bookId = rs.getInt("book_id");
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        }



        DruidUtil.close(conn, pstmt, rs);

        // 如果受影响的行数大于0，说明更新成功
        return affectedRows > 0;
    }


}
