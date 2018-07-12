package com.softserve.edu.library.servlet;

import com.softserve.edu.library.service.AuthorService;
import org.json.simple.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auto")
public class AutoCompleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthorService authorService = new AuthorService();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String query = request.getParameter("query");

        JSONArray json = authorService.getAuthorsByQuery(query);

        response.setContentType("application/json");
        response.getWriter().print(json);
        System.out.println(json);
    }
}
