package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.BookDto;
import com.softserve.edu.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo: delete hardcode
        //List<BookDto> books = bookService.executeBookSearch("", "bookName", 1, 10);
        List<BookDto> books = bookService.getBooks();
//        int numberOfRows = bookService.getNumberOfBooks(searchKey);
//        int nOfPages = numberOfRows / 10;
//
//        if (nOfPages % 10 > 0) {
//            nOfPages++;
//        }

        request.setAttribute("listBook", books);
//        request.setAttribute("searchKey", "");
//        request.setAttribute("nOfPages", nOfPages);
//        request.setAttribute("currentPage", 1);
//        request.setAttribute("rowsPerPage", 10);


        request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rowsPerPage = Integer.parseInt(req.getParameter("rowsPerPage"));
        String checkBy = req.getParameter("checkBy");
        String searchKey = req.getParameter("searchKey");
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));

        List<BookDto> bookList = bookService.executeBookSearch(searchKey, checkBy, currentPage, rowsPerPage);

        //todo:rewrite getNumberOfBooks to get number of search books
        int numberOfRows = bookService.getNumberOfBooks(searchKey);
        int nOfPages = numberOfRows / rowsPerPage;

        if (nOfPages % rowsPerPage > 0) {
            nOfPages++;
        }

        req.setAttribute("listBook", bookList);
        req.setAttribute("searchKey", searchKey);
        req.setAttribute("nOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("rowsPerPage", rowsPerPage);

        req.getRequestDispatcher("/pages/search.jsp").forward(req, resp);
    }
}


