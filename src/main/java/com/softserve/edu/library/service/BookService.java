package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.BookDao;
import com.softserve.edu.library.dto.AuthorDto;
import com.softserve.edu.library.dto.BookDto;
import com.softserve.edu.library.dto.BookSearchDto;
import com.softserve.edu.library.entity.Author;
import com.softserve.edu.library.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookDao bookDao = new BookDao();

    private BookDto convert(Book book) {
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            AuthorDto authorDto = new AuthorDto();
            authorDto.setFirstName(author.getFirstName());
            authorDto.setLastName(author.getLastName());

            authorDtos.add(authorDto);
        }
        return new BookDto(book.getId(),
                book.getName(),
                authorDtos,
                book.getPublicationDate(),
                String.valueOf(book.getAvailable()),
                book.getNumberOfTaken());
    }

    public int getNumberOfBooks(BookSearchDto bookSearchDto) {
        if (bookSearchDto.getCheckBy().equals("publicationDate") && !bookSearchDto.getSearchKey().equals("")) {
            String[] dates = bookSearchDto.getSearchKey().split("-");
            if (dates.length == 1) {
                return bookDao.getNumberOfBooksByDate(dates[0], "2020");
            }
            return bookDao.getNumberOfBooksByDate(dates[0], dates[1]);
        } else {
            return bookDao.getNumberOfBooksByName(bookSearchDto.getSearchKey());
        }
    }

    public List<BookDto> executeBookSearch(BookSearchDto bookSearchDto) {
        List<BookDto> bookList = null;

        String checkBy = bookSearchDto.getCheckBy();
        String searchKey = bookSearchDto.getSearchKey();
        int rowsPerPage = bookSearchDto.getRowsPerPage();
        int currentPage = bookSearchDto.getCurrentPage();
        String sort = bookSearchDto.getSort();
        int start = currentPage * rowsPerPage - rowsPerPage;

        if (checkBy.equals("bookName")) {
            bookList = searchByBookName(searchKey, sort, start, rowsPerPage);
        } else if (checkBy.equals("author")) {
            bookList = searchByAuthor(searchKey, sort, start, rowsPerPage);
        } else if (checkBy.equals("publicationDate")) {
            bookList = searchByPublicationDate(searchKey, sort, start, rowsPerPage);
        }
        return bookList;
    }

    private List<BookDto> searchByBookName(String searchKey, String sort, int start, int rowsPerPage) {
        List<Book> bookByKey = bookDao.getBookByKey(searchKey, sort, start, rowsPerPage);
        List<BookDto> dtoList = new ArrayList<>();
        for (Book book : bookByKey) {
            dtoList.add(convert(book));
        }
        return dtoList;
    }

    private List<BookDto> searchByAuthor(String author, String sort, int start, int rowsPerPage) {
        List<BookDto> dtoList = new ArrayList<>();
        String[] split;
        if (author.equals("")) {
            dtoList = searchByBookName("", sort, start, rowsPerPage);
            return dtoList;
        } else {
            split = author.split(" ");
        }

        List<Book> bookByKey;
        if (split.length == 1) {
            List<Book> firstNameList = bookDao.getBooksByAuthor(split[0], "", sort, start, rowsPerPage);
            List<Book> lastNameList = bookDao.getBooksByAuthor("", split[0], sort, start, rowsPerPage);
            bookByKey = mergeListBooks(firstNameList, lastNameList);
        } else {
            bookByKey = bookDao.getBooksByAuthor(split[0], split[1], sort, start, rowsPerPage);
        }

        for (Book book : bookByKey) {
            dtoList.add(convert(book));
        }
        return dtoList;
    }

    private List<BookDto> searchByPublicationDate(String key, String sort, int start, int rowsPerPage) {
        List<BookDto> dtoList = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();
        String[] dates = key.split("-");
        if (key.isEmpty()) {
            bookList = bookDao.getAllBooks(sort, start, rowsPerPage);
        } else if (dates.length == 1 && !key.contains("-")) {
            bookList = bookDao.getBooksByDate(key, sort, start, rowsPerPage);
        } else if (dates.length == 1 && key.contains("-")) {
            dates = new String[]{dates[0], ""};
        } else if (dates.length == 0) dates = new String[]{"", ""};
        if (key.contains("-")) {
            bookList = bookDao.getBooksByDatePeriod(dates[0], dates[1], sort, start, rowsPerPage);
        }
        for (Book book : bookList) {
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

    public BookDto getByName(String name) {
        Book book = bookDao.getByName(name);
        BookDto bookDto = convert(book);
        return bookDto;
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