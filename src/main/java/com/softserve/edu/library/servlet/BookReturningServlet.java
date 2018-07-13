package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.BookReturningDto;
import com.softserve.edu.library.dto.BookTakingDto;
import com.softserve.edu.library.service.BookReturningService;
import com.softserve.edu.library.service.BookTakingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/returnBook")
public class BookReturningServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookReturningDto bookReturningDto = new BookReturningDto(
                Long.valueOf(req.getParameter("userID")),
                Long.valueOf(req.getParameter("bookId")));
        BookReturningService bookReturningService = new BookReturningService();


        bookReturningService.returnBook(bookReturningDto);

        HttpSession session = req.getSession();
//        req.setAttribute("bookTakeResultMessage", resultString);
//        req.setAttribute("bookMessageStyle", "display: block");

        req.getRequestDispatcher("/takenBooks").forward(req, resp);
    }
}
