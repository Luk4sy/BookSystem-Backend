package com.example.javaweblab3.servlet.user;

import com.example.javaweblab3.entity.User;
import com.example.javaweblab3.service.UserService;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    private Gson gson;

    public LoginServlet() {
        this.userService = new UserService();
        this.gson = new Gson();
    }

    //解决跨域问题
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set access control headers
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //请求中读取JSON数据
        BufferedReader reader = request.getReader();
        User user = gson.fromJson(reader, User.class);
        System.out.println("用户：" + user);

        //验证用户
        User authenticatedUser;
        try {
            authenticatedUser = userService.userLogin(user.getUsername(), user.getPassword());
            System.out.println(authenticatedUser);
            System.out.println("登录验证成功");
            //session中存入user的id
            HttpSession session = request.getSession();
            session.setAttribute("userId", authenticatedUser.getId());
            System.out.println(session.getId());
            System.out.println(session.getAttribute("userId"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("登录失败", e);
        }


        //创建响应数据
        Map<String, Object> responseData = new HashMap<>();
        if (authenticatedUser != null) {
            responseData.put("success", true);
            responseData.put("user", authenticatedUser);
        } else {
            responseData.put("success", false);
        }

        //响应数据写入JSON
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(responseData));


    }
}
