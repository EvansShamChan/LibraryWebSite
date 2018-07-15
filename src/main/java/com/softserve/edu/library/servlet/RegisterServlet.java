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
        RegisterDto registerDto = new RegisterDto();
        UserService userService = new UserService();
        request.setCharacterEncoding("UTF-8");
        fillUpRegisterDto(request, registerDto);
        System.out.println(registerDto.getDate());
        boolean isUserPresent = userService.isUserPresent(registerDto);
        if (!isUserPresent) {
            userService.addNewUser(registerDto);
        } else {
            prepareDataToReturn("userSameError", request, registerDto);
            request.getRequestDispatcher("/pages/registerPage.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/pages/signIn.jsp").forward(request, response);
    }

    private void fillUpRegisterDto(HttpServletRequest request, RegisterDto registerDto) {
        String dateString = request.getParameter("dateOfBirth");
        registerDto.setUsername(request.getParameter("username"));
        registerDto.setPassword(request.getParameter("password"));
        registerDto.setFirstName(request.getParameter("firstname"));
        registerDto.setLastName(request.getParameter("lastname"));
        registerDto.setDate(DateService.parseStringToSqlDate(dateString));
    }

    private void prepareDataToReturn(String errorName, HttpServletRequest req, RegisterDto registerDto) {
        req.setAttribute(errorName, "display: block");
        req.setAttribute("usernameAtr", registerDto.getUsername());
        req.setAttribute("passwordAtr", registerDto.getPassword());
        req.setAttribute("firstnameAtr", registerDto.getFirstName());
        req.setAttribute("lastnameAtr", registerDto.getLastName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
