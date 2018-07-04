package com.softserve.edu.library.servlet;

import com.softserve.edu.library.dto.BookDto;
import com.softserve.edu.library.entity.Book;
import com.softserve.edu.library.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

public class BookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getRequestURI();
        try {
            switch (action) {
                case "/search/new":
                    showNewForm(request, response);
                    break;
                case "/search/edit":
                    showEditForm(request, response);
                    break;
                case "/search/insert":
                    insertBook(request, response);
                    break;
                case "/search/update":
                    updateBook(request, response);
                    break;
                case "/search/delete":
                    deleteBook(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException {
        BookService bookService = new BookService();
        List<BookDto> books = bookService.getBooks();
        request.setAttribute("listBook", books);
        request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bookForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookService bookService = new BookService();
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        Book existingBook = bookService.getByName(name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        BookService bookService = new BookService();
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String publicationDate = request.getParameter("publicationDate");
        Long available = Long.parseLong(request.getParameter("available"));

        Book newBook = new Book(name, publicationDate, available);
        boolean result = bookService.insertBook(newBook);
        request.setAttribute("success", result);//TODO add message on frontend
        try {
            request.getRequestDispatcher("/searchPag").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        BookService bookService = new BookService();
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String publicationDate = request.getParameter("publicationDate");
        Long available = Long.parseLong(request.getParameter("available"));
        Book book = new Book(name, publicationDate, available);
        bookService.updateBook(book);
        try {
            request.getRequestDispatcher(String.valueOf(session.getAttribute("lastSearchUrl"))).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        BookService bookService = new BookService();
        String name = request.getParameter("name");
        bookService.deleteBook(name);
        try {
            request.getRequestDispatcher("/searchPag").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}


