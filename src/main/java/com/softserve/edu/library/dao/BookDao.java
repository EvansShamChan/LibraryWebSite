package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Book;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private AuthorToBookDao authorToBookDao = new AuthorToBookDao();

    public Book getById(String id) {
        Statement statement = null;
        Book result;
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            // TODO CHECK!
            statement.executeQuery("SELECT * FROM books WHERE id = " + id + ";");
            ResultSet resultSet = statement.getResultSet();
            result = new Book();
            while (resultSet.next()) {
                result = parseBooks(resultSet);
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

    public List<Book> getAllBooks() {
        Statement statement = null;
        List<Book> result = new ArrayList<>();
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            // TODO CHECK!
            statement.executeQuery("SELECT * FROM books");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                result.add(parseBooks(resultSet));
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

    private Book parseBooks(ResultSet resultSet) throws Exception {
        Book result = new Book();

        result.setId(resultSet.getInt("id"));
        result.setName(resultSet.getString("name"));
        result.setPublicationDate(resultSet.getString("publication_date"));
        result.setAvailable(resultSet.getInt("available"));
        result.setAuthors(authorToBookDao.getAuthorsByBookId(String.valueOf(resultSet.getInt("id"))));

        return result;
    }
}


