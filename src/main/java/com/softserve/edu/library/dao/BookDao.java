package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private AuthorToBookDao authorToBookDao = new AuthorToBookDao();

    public Book getByName(String name) {
        PreparedStatement statement = null;
        Book result = null;
        try {
            statement = ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM books WHERE name = ?;");

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

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

    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (name, publication_date, available) VALUES (?, ?, ?)";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1, book.getName());
        statement.setString(2, book.getPublicationDate());
        statement.setLong(3, book.getAvailable());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        return rowInserted;
    }

    public boolean deleteBook(String name) throws SQLException {
        String sql = "DELETE FROM books where name = ?";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1, name);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET publication_date = ?, available = ? WHERE name = ?";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1, book.getPublicationDate());
        statement.setLong(2, book.getAvailable());
        statement.setString(3, book.getName());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        return rowUpdated;
    }

    private Book parseBooks(ResultSet resultSet) throws Exception {
        Book result = new Book();

        result.setId(resultSet.getInt("id"));
        result.setName(resultSet.getString("name"));
        result.setPublicationDate(resultSet.getString("publication_date"));
        result.setAvailable(resultSet.getLong("available"));
        result.setAuthors(authorToBookDao.getAuthorsByBookId(String.valueOf(resultSet.getInt("id"))));

        return result;
    }
}


