import com.example.javaweblab3.util.DruidUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DruidTest {
    public static void main(String[] args) {
        try (Connection conn = DruidUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM user")) {
            // 处理结果集
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println("username: " + username + ", password: " + password);
            }
        } catch (SQLException e) {
            // 处理 SQL 异常
            e.printStackTrace();
        }
    }
}
