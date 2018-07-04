package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.RegisterDto;
import com.softserve.edu.library.service.DateService;
import com.softserve.edu.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateString = request.getParameter("dateOfBirth");
        RegisterDto registerDto = new RegisterDto();
        UserService userService = new UserService();
        try {
            registerDto.setUsername(request.getParameter("username"));
            registerDto.setPassword(request.getParameter("password"));
            registerDto.setFirstName(request.getParameter("firstname"));
            registerDto.setLastName(request.getParameter("lastname"));
            registerDto.setDate(DateService.parseStringToSqlDate(dateString));
        } catch (ParseException e) {
            prepareDataToReturn("dateErrorStyle", request, registerDto);
            request.getRequestDispatcher("/pages/sign-up/registerPage.jsp").forward(request, response);
            return;
        }
        boolean isUserPresent = userService.isUserPresent(registerDto);
        if (isUserPresent) {
            userService.addNewUser(registerDto);
        } else {
            prepareDataToReturn("userSameError", request, registerDto);
            request.getRequestDispatcher("/pages/sign-up/registerPage.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/pages/sign-in/SignIn.jsp").forward(request, response);
    }

    private void prepareDataToReturn(String errorName, HttpServletRequest req, RegisterDto registerDto) {
        req.setAttribute(errorName, "display: block");
        req.setAttribute("usernameAtr", registerDto.getUsername());
        req.setAttribute("passwordAtr", registerDto.getPassword());
        req.setAttribute("firstnameAtr", registerDto.getFirstName());
        req.setAttribute("lastnameAtr", registerDto.getLastName());
    }
}
