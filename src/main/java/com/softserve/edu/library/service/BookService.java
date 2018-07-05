package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.BookDao;
import com.softserve.edu.library.dto.BookDto;
import com.softserve.edu.library.entity.Author;
import com.softserve.edu.library.entity.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            bookList = searchByAuthor(searchKey, start, rowsPerPage);
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

    private List<BookDto> searchByAuthor(String author, int start, int rowsPerPage) {
        List<BookDto> dtoList = new ArrayList<>();
        String[] split;
        if(author.equals("")){
            dtoList = searchByBookName("", start, rowsPerPage);
            return dtoList;
        } else {
            split = author.split(" ");
        }

        List<Book> bookByKey;
        if (split.length == 1) {
            //todo: methods return empty list
            List<Book> firstNameList = bookDao.getBooksByAuthor(split[0], "", start, rowsPerPage);
            List<Book> lastNameList = bookDao.getBooksByAuthor("", split[0], start, rowsPerPage);
            bookByKey = mergeListBooks(firstNameList, lastNameList);
        } else {
            bookByKey = bookDao.getBooksByAuthor(split[0], split[1], start, rowsPerPage);
        }

        for (Book book : bookByKey) {
            dtoList.add(convert(book));
        }
        return dtoList;
    }

    private List<Book> mergeListBooks(List<Book> firstNameList, List<Book> lastNameList) {
        List<Book> fullList = new ArrayList<>(firstNameList);
        for (Book book : lastNameList) {
            fullList.add(book);
        }
        return fullList;
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
