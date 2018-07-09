package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.BookTakingDto;
import com.softserve.edu.library.service.BookTakingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/takeTheBook")
public class BookTakingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookTakingDto bookTakingDto = new BookTakingDto(
                Long.valueOf(req.getParameter("userID")),
                Long.valueOf(req.getParameter("bookId")),
                Long.valueOf(req.getParameter("available")));
        BookTakingService bookTakingService = new BookTakingService();
        String resultString = bookTakingService.takeBook(bookTakingDto);
//        req.setAttribute("bookWasSuccessfullyAdded", "display: block");
//        req.getRequestDispatcher("/pages/userSearchBookPage.jsp").forward(req, resp);


        HttpSession session = req.getSession();
        req.setAttribute("bookWasSuccessfullyAdded", "display: block");


        req.getRequestDispatcher(String.valueOf(session.getAttribute("lastSearchUrl"))).forward(req, resp);


//        resp.sendRedirect(String.valueOf(session.getAttribute("lastSearchUrl")));

    }

}
