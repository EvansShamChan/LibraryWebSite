package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.BookDto;
import com.softserve.edu.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchPag")
public class BookSearchPaginationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("rowsPerPage") == null) {
            req.getRequestDispatcher("/pages/search.jsp").forward(req, resp);
        }

        BookService bookService = new BookService();


        int rowsPerPage = Integer.parseInt(req.getParameter("rowsPerPage"));
        String checkBy = req.getParameter("checkBy");
        String searchKey = req.getParameter("searchKey");
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));

        List<BookDto> bookList = bookService.executeBookSearch(searchKey, checkBy, currentPage, rowsPerPage);

        int numberOfRows = bookService.getNumberOfBooks(searchKey);
        int nOfPages = numberOfRows / rowsPerPage;

        if (nOfPages % rowsPerPage > 0) {
            nOfPages++;
        }

        HttpSession session = req.getSession();

        req.setAttribute("listBook", bookList);
        req.setAttribute("searchKey", searchKey);
        req.setAttribute("nOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("rowsPerPage", rowsPerPage);


        String URL = req.getRequestURI() + "?" + req.getQueryString();
        session.setAttribute("lastSearchUrl", URL);

        req.getRequestDispatcher("/pages/search.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
