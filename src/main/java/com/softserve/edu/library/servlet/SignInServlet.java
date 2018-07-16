package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.LoginDto;
import com.softserve.edu.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        LoginDto loginDto = new LoginDto(request.getParameter("username"), request.getParameter("password"));
        UserService userService = new UserService();

        String[] isLoginValid = userService.isUserPresent(loginDto);

        if (isLoginValid != null) {
            if (isLoginValid[0].equals("user") || isLoginValid[0].equals("admin")) {
                HttpSession session = request.getSession();
                session.setAttribute("userOrAdmin", isLoginValid[0]);
                session.setAttribute("userID", Long.valueOf(isLoginValid[1]));
                response.sendRedirect("/searchPag?searchKey=&rowsPerPage=10&checkBy=bookName&currentPage=1&sort=desc");
            } else {
                request.setAttribute("errorStyle", "display: block");
                request.getRequestDispatcher("pages/signIn.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}