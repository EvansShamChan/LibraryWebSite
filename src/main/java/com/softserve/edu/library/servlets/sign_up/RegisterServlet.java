package com.softserve.edu.library.servlets.sign_up;

import com.softserve.edu.library.dao.UserDao;
import com.softserve.edu.library.dto.LoginDto;
import com.softserve.edu.library.dto.RegisterDto;
import com.softserve.edu.library.entity.User;
import com.softserve.edu.library.service.DateService;
import com.softserve.edu.library.service.UserService;

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateString = req.getParameter("dateOfBirth");
        RegisterDto registerDto = new RegisterDto();
        UserService userService = new UserService();
        try {
            registerDto.setUsername(req.getParameter("username"));
            registerDto.setPassword(req.getParameter("password"));
            registerDto.setFirstName(req.getParameter("firstname"));
            registerDto.setLastName(req.getParameter("lastname"));
            registerDto.setDate(DateService.parseStringToSqlDate(dateString));
        } catch (ParseException e) {
            prepareDataToReturn("dateErrorStyle", req, registerDto);
            req.getRequestDispatcher("/pages/sign-up/registerPage.jsp").forward(req, resp);
            return;
        }
        boolean isUserPresent = userService.isUserPresent(registerDto);
        if(isUserPresent) {
            userService.addNewUser(registerDto);
        } else {
            prepareDataToReturn("userSameError", req, registerDto);
            req.getRequestDispatcher("/pages/sign-up/registerPage.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/pages/sign-in/SignIn.jsp").forward(req, resp);
    }

    private void prepareDataToReturn(String errorName, HttpServletRequest req, RegisterDto registerDto) {
        req.setAttribute(errorName, "display: block");
        req.setAttribute("usernameAtr", registerDto.getUsername());
        req.setAttribute("passwordAtr", registerDto.getPassword());
        req.setAttribute("firstnameAtr", registerDto.getFirstName());
        req.setAttribute("lastnameAtr", registerDto.getLastName());
    }
}
