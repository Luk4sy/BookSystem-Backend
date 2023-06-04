package com.example.javaweblab3.servlet.book;

import com.example.javaweblab3.entity.Book;
import com.example.javaweblab3.service.BookService;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BookShowServlet", value = "/BookShowServlet")
public class BookShowServlet extends HttpServlet {

    private BookService bookService;
    private Gson gson;

    public BookShowServlet() {
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
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            List<Book> books = bookService.getAllBooks();
            PrintWriter out = response.getWriter();
            out.println(gson.toJson(books));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("获取书籍失败", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
