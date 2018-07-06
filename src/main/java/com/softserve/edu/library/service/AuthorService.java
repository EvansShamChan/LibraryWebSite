package com.softserve.edu.library.service;

import com.softserve.edu.library.dao.AuthorDao;
import com.softserve.edu.library.dao.AuthorToBookDao;
import com.softserve.edu.library.dao.BookDao;
import com.softserve.edu.library.entity.Author;
import com.softserve.edu.library.entity.Book;

import java.sql.SQLException;

public class AuthorService {
    private BookDao bookDao = new BookDao();
    private AuthorDao authorDao = new AuthorDao();
    private AuthorToBookDao authorToBookDao = new AuthorToBookDao();


    public boolean deleteAuthorFromBook(String bookName, String firstName, String lastName) throws SQLException {
        Book book = bookDao.getByName(bookName);
        Author author = authorDao.getByName(firstName, lastName);
        if (book != null && author != null) {
            long idBook = book.getId();
            long idAuthor = author.getId();

            return authorToBookDao.deleteAuthorToBook(idBook, idAuthor);
        }
        return false;
    }
}
