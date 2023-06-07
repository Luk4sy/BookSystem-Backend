package com.example.javaweblab3.servlet.borrowrecord;

import com.example.javaweblab3.entity.BorrowRecord;
import com.example.javaweblab3.service.BorrowRecordService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RecordAddServlet", value = "/RecordAddServlet")
public class RecordAddServlet extends HttpServlet {

    private BorrowRecordService borrowRecordService;
    private Gson gson;

    public RecordAddServlet() {
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
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        System.out.println("进入dopost");

        // 从请求中解析JSON数据
        StringBuilder sb = new StringBuilder();
        String line;
        System.out.println("解析数据");

        try {
            System.out.println("进入try");
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            System.out.println("获取数据1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String requestData = sb.toString();
        if (requestData.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        JsonObject jsonObject = null;
        try {
            jsonObject = JsonParser.parseString(requestData).getAsJsonObject();
            System.out.println("获取数据2");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("JSON data: " + jsonObject.toString());
        int bookId = jsonObject.get("bookId").getAsInt();
        System.out.println("获取bookId");

        // 从字符串解析日期到java.util.Date
        String dateString = jsonObject.get("date").getAsString();
        System.out.println("获取dateString");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date returnDate = null;
        try {
            returnDate = format.parse(dateString);
            System.out.println("date转换格式");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 从session中获取用户id
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        System.out.println(session.getAttribute("userId"));
        int userId = (int) session.getAttribute("userId");
        System.out.println("userid : " + userId);

        try {
            // 调用borrowRecordService的borrowBook方法
            borrowRecordService.addRecord(userId, bookId, returnDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String flag = "借书成功！";
        String json = gson.toJson(flag);
        response.getWriter().write(json);
    }
}
