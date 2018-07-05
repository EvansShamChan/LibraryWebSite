package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.LoginDto;
import com.softserve.edu.library.service.UserService;

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
        LoginDto loginDto = new LoginDto(request.getParameter("username"), request.getParameter("password"));
        UserService userService = new UserService();

        String isLoginValid = userService.isUserPresent(loginDto);

        if (isLoginValid != null) {
            if (isLoginValid.equals("user")) {
                //todo URL for user page after log in
            } else if (isLoginValid.equals("admin")) {
                request.getRequestDispatcher("/searchPag").forward(request, response);
            } else {
                request.setAttribute("errorStyle", "display: block");
                request.getRequestDispatcher("pages/SignIn.jsp").forward(request, response);
            }
        }
    }
}