package com.softserve.edu.library.servlet;

import com.softserve.edu.library.service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/author/delete")
public class AuthorDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        AuthorService authorService = new AuthorService();
        String bookName = request.getParameter("bookName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        try {
            authorService.deleteAuthorFromBook(bookName, firstName, lastName) ;
            request.getRequestDispatcher("/search/edit?name=" + bookName).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
