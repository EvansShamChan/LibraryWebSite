package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.DeptorsDto;
import com.softserve.edu.library.service.AdminOptionsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/options/*")
public class UserOptionsForAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.equals("/options")) {
            req.getRequestDispatcher("pages/userOptionsForAdminPage.jsp").forward(req, resp);
        } else {
            executeOptions(uri, req, resp);
        }

    }

    private void executeOptions(String uri, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminOptionsService optionsService = new AdminOptionsService();
        switch (uri) {
            case "/options/userStatistic":
                Map<String, Integer> userAvgStatistic = optionsService.getUserAvgStatistic();
                req.setAttribute("statistic", userAvgStatistic);
                req.getRequestDispatcher("/options").forward(req, resp);
                break;
            case "/options/deptors":
                List<DeptorsDto> deptors = optionsService.getDeptors();
                req.setAttribute("deptors", deptors);
                req.getRequestDispatcher("/options").forward(req, resp);
                break;
        }
    }
}
