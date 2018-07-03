package com.softserve.edu.library.servlets.sign_in;

import com.softserve.edu.library.dao.UserDao;
import com.softserve.edu.library.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.getCredentialsForLogin(userName, password);

        if (!(user.getUsername() == null && user.getPassword() == null)) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                request.getRequestDispatcher("/search").forward(request, response);
            }
        } else {
            request.setAttribute("errorStyle", "display: block");
            request.getRequestDispatcher("pages/sign-in/SignIn.jsp").forward(request, response);
        }
    }
}