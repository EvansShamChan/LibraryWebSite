package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.BookDto;
import com.softserve.edu.library.dto.BookSearchDto;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        if (req.getParameter("rowsPerPage") == null) {
            req.getRequestDispatcher("/searchPag?searchKey=&rowsPerPage=10&checkBy=bookName&currentPage=1").forward(req, resp);
        }
        BookService bookService = new BookService();

        BookSearchDto bookSearchDto = new BookSearchDto(
                Integer.parseInt(req.getParameter("rowsPerPage")),
                req.getParameter("checkBy"), req.getParameter("searchKey"),
                Integer.parseInt(req.getParameter("currentPage")));

        List<BookDto> bookList = bookService.executeBookSearch(bookSearchDto);

        int numberOfRows = bookService.getNumberOfBooks(bookSearchDto);
        int nOfPages = numberOfRows / bookSearchDto.getRowsPerPage();
        if(numberOfRows == 10) {
            //nop
        } else if (nOfPages % bookSearchDto.getRowsPerPage() > 0) {
            ++nOfPages;
        }
        setResultSearchInRequestAttributes(req, bookSearchDto, nOfPages, bookList);
        putInSessionLastSearchURL(req, session);
        redirectByRole(req, resp, session);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void setResultSearchInRequestAttributes(HttpServletRequest req, BookSearchDto bookSearchDto, int nOfPages, List<BookDto> bookList) {
        req.setAttribute("listBook", bookList);
        req.setAttribute("searchKey", bookSearchDto.getSearchKey());
        req.setAttribute("nOfPages", nOfPages);
        req.setAttribute("currentPage", bookSearchDto.getCurrentPage());
        req.setAttribute("rowsPerPage", bookSearchDto.getRowsPerPage());
        req.setAttribute("checkBy", bookSearchDto.getCheckBy());
    }

    private void putInSessionLastSearchURL(HttpServletRequest req, HttpSession session) {
        String URL = req.getRequestURI() + "?" + req.getQueryString();
        session.setAttribute("lastSearchUrl", URL);
    }

    private void redirectByRole(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        if (session.getAttribute("userOrAdmin").equals("user")) {
            req.getRequestDispatcher("/pages/userSearchBookPage.jsp").forward(req, resp);
        } else if (session.getAttribute("userOrAdmin").equals("admin")) {
            req.getRequestDispatcher("/pages/adminSearchBookPage.jsp").forward(req, resp);
        }
    }
}

