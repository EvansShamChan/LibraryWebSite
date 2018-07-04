package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.BookDao;
import com.softserve.edu.library.dto.BookDto;
import com.softserve.edu.library.entity.Author;
import com.softserve.edu.library.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookDao bookDao = new BookDao();

    public List<BookDto> getBooks() {
        List<Book> list = bookDao.getAllBooks();

        List<BookDto> dtoList = new ArrayList<>();
        for (Book book : list) {
            dtoList.add(convert(book));
        }
        return dtoList;
    }

    private BookDto convert(Book book) {
        String authors = "";
        for (Author author : book.getAuthors()) {
            if (!authors.isEmpty()) {
                authors += ", ";
            }
            authors += author.getFirstName() + " " + author.getLastName();
        }
        return new BookDto(book.getName(), authors, book.getPublicationDate(), String.valueOf(book.getAvailable()));
    }

    public int getNumberOfBooks(String searchKey) {
        return bookDao.getNumberOfBooks(searchKey);
    }

    public List<BookDto> executeBookSearch(String searchKey, String checkBy, int currentPage, int rowsPerPage) {
        List<BookDto> bookList = null;
        int start = currentPage * rowsPerPage - rowsPerPage;
        if (checkBy.equals("bookName")) {
            bookList = searchByBookName(searchKey, start, rowsPerPage);
        } else if (checkBy.equals("author")) {

        }
        return bookList;
    }

    private List<BookDto> searchByBookName(String searchKey, int start, int rowsPerPage) {
        List<Book> bookByKey = bookDao.getBookByKey(searchKey, start, rowsPerPage);
        List<BookDto> dtoList = new ArrayList<>();
        for (Book book : bookByKey) {
            dtoList.add(convert(book));
        }
        return dtoList;
    }

    public Book getByName(String name) {
        return bookDao.getByName(name);
    }

    public boolean insertBook(Book book) {
        try {
            return bookDao.insertBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBook(Book book) {
        try {
            return bookDao.updateBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBook(String name) {
        try {
            return bookDao.deleteBook(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
