package com.example.javaweblab3.servlet.borrowrecord;

import com.example.javaweblab3.entity.BorrowRecord;
import com.example.javaweblab3.service.BorrowRecordService;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecordShowServlet", value = "/RecordShowServlet")
public class RecordShowServlet extends HttpServlet {

    private BorrowRecordService borrowRecordService;
    private Gson gson;

    public RecordShowServlet() {
        this.borrowRecordService = new BorrowRecordService();
        this.gson = new Gson();
    }

    //解决跨域问题
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // set access control headers
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("进入RecordShow");
        // 获取所有借阅记录
        List<BorrowRecord> records = null;
        try {
            System.out.println("进入RecordShow-try");
            records = borrowRecordService.getAllRecords();
            System.out.println("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("获取记录失败", e);
        }

        // 将记录转换为 JSON 格式
        String json = gson.toJson(records);

        // 设置响应内容类型为 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 发送响应
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
