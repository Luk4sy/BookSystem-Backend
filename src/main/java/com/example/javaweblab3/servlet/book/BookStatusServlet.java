package com.example.javaweblab3.servlet.book;

import com.example.javaweblab3.service.BookService;
import com.example.javaweblab3.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BookStatusServlet", value = "/BookStatusServlet")
public class BookStatusServlet extends HttpServlet {

    private BookService bookService;
    private Gson gson;

    public BookStatusServlet() {
        this.bookService = new BookService();
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

        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
        }
        JsonObject jsonObject = new Gson().fromJson(sb.toString(), JsonObject.class);

        String bookIdStr = jsonObject.get("id").getAsString();
        String status = jsonObject.get("status").getAsString();

        if (bookIdStr != null && status != null) {
            int bookId = Integer.parseInt(bookIdStr);
            try {
                bookService.updateBookStatus(bookId, status);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
