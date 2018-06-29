package com.softserve.edu.library.servlets.sign_up;

import com.softserve.edu.library.dao.UserDao;
import com.softserve.edu.library.entity.User;
import com.softserve.edu.library.service.DateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserDao userDao = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo: write redirect if datebirth is incorrect
        String username = req.getParameter("username");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        Date date_of_birth = null;
        try {
            date_of_birth = DateService.parseStringToSqlDate(req.getParameter("dateOfBirth"));
        } catch (ParseException e) {
            req.setAttribute("dateErrorStyle", "display: block");
            req.getRequestDispatcher("/pages/sign-up/registerPage.jsp").forward(req, resp);
        }
        Date currentDate = DateService.getCurrentSqlDate();

        User user = new User(firstname, lastname, date_of_birth, currentDate, username, password);

        userDao = new UserDao();
        userDao.addUser(user);
    }
}
