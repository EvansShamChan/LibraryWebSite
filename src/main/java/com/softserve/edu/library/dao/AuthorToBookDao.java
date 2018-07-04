package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Author;
import com.softserve.edu.library.entity.AuthorToBook;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorToBookDao {

    private AuthorDao authorDao = new AuthorDao();

    public List<AuthorToBook> getAuthorByBookId(String id) {
        Statement statement = null;
        List<AuthorToBook> result = new ArrayList<>();
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            // TODO CHECK!
            statement.executeQuery("SELECT id_author, id_book FROM authors_to_books WHERE id_book = " + id + ";");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                result.add(parseAuthorToBook(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex.getMessage());
                }
            }
        }
        return result;
    }

    public List<Author> getAuthorsByBookId(String bookId) {
        List<Author> result = new ArrayList<>();
        List<AuthorToBook> authorByBookId = getAuthorByBookId(bookId);
        for (AuthorToBook authorToBook : authorByBookId) {
            Author author = authorDao.getById(String.valueOf(authorToBook.getIdAuthor()));
            result.add(author);
        }

        return result;

    }

    private AuthorToBook parseAuthorToBook(ResultSet resultSet) throws Exception {
        AuthorToBook result = new AuthorToBook();

        result.setIdAuthor(resultSet.getInt("id_author"));
        result.setIdBook(resultSet.getInt("id_book"));
        return result;
    }
}
