package com.example.moviestar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static Map<String, String> accounts;
    @Override
    public void init() throws ServletException {
        System.out.println("Begin init");
        accounts = new HashMap<String, String>();
        accounts.put("admin", "123");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Ex01_war/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        if (login(request, response)) {
            printWriter.write("Name/Password match");
        } else {
            printWriter.write("Name/Password does not match");
        }
    }
    public boolean login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (accounts.containsKey(username)) {
            return accounts.get(username).equals(password);
        } else {
            return false;
        }
    }
}
