package com.example.javaweblab3.servlet.borrowrecord;

import com.example.javaweblab3.service.BorrowRecordService;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RecordReturnServlet", value = "/RecordReturnServlet")
public class RecordReturnServlet extends HttpServlet {
    private BorrowRecordService borrowRecordService;
    private Gson gson;

    public RecordReturnServlet() {
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

        System.out.print("enter return");
        // 从请求中获取 recordId
        int recordId = Integer.parseInt(request.getParameter("recordId"));

        try {
            // 尝试还书
            boolean success = borrowRecordService.returnBook(recordId);
            System.out.print("enter return-try");
            // 根据结果返回相应的响应
            if (success) {
                response.getWriter().write(gson.toJson("Return success"));
            } else {
                response.getWriter().write(gson.toJson("Return failed"));
            }

        } catch (Exception e) {
            // 如果有异常，返回错误信息
            response.getWriter().write(gson.toJson("Error: " + e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
