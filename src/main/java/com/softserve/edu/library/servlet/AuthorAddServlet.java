package com.softserve.edu.library.servlet;

import com.softserve.edu.library.exception.DuplicateAuthorException;
import com.softserve.edu.library.service.AuthorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/author/add")
public class AuthorAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        AuthorService authorService = new AuthorService();
        String bookName = request.getParameter("bookName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        try {
            authorService.addAuthorToBook(bookName, firstName, lastName);
            request.getRequestDispatcher("/search/edit?name=" + bookName).forward(request, response);
        } catch (DuplicateAuthorException s) {
            throw s;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}