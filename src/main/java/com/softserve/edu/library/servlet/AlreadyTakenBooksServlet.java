package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.TakenBookDto;
import com.softserve.edu.library.service.BookTakingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/takenBooks")
public class AlreadyTakenBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookTakingService bookTakingService = new BookTakingService();
        List<TakenBookDto> allBooksByUserId = bookTakingService.getAllTakenBooksByUserId(Long.valueOf(req.getParameter("userID")));

        req.setAttribute("allBooksByUserId", allBooksByUserId);
        req.getRequestDispatcher("/pages/userTakenBooks.jsp").forward(req, resp);
    }
}