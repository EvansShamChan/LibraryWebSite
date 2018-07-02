package com.softserve.edu.library.servlets.sign_in;

import com.softserve.edu.library.dao.SignInDao;
import com.softserve.edu.library.entity.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignInOld")
public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        SignInDao signInDao = new SignInDao();
        User user = signInDao.getUserByUserName(userName, password);

        if (!(user.getUsername() == null && user.getPassword() == null)) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                //URL for page after SignIN
            }
        } else {
            request.setAttribute("errorStyle", "display: block");
            request.getRequestDispatcher("pages/sign-in/SignIn.jsp").forward(request, response);
        }
    }
}