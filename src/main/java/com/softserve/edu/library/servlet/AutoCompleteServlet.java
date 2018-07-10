package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dao.AuthorDao;
import com.softserve.edu.library.dao.BookDao;
import com.softserve.edu.library.db.ConnectionManager;
import org.json.simple.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

@WebServlet("/auto")
public class AutoCompleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthorDao authorDao = new AuthorDao();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String query = request.getParameter("query");

        JSONArray json = new JSONArray();

        try {
            json.addAll(authorDao.getAllByFirstName(query));
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.getWriter().print(json);
        System.out.println(json);
    }
}





