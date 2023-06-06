package com.example.javaweblab3.dao.daoimp;

import com.example.javaweblab3.dao.UserDao;
import com.example.javaweblab3.entity.User;
import com.example.javaweblab3.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImp implements UserDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public User userLogin(String username, String password) throws Exception {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        conn = DruidUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        System.out.println(username);
        pstmt.setString(2, password);
        System.out.println(password);
        rs = pstmt.executeQuery();
        User user = null;

        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            System.out.println("add!!!!");
        }

        DruidUtil.close(conn, pstmt, rs);
        return user;
    }
}
