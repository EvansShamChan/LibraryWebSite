package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class AuthorDao {

    public Author getById(String id) {
        Statement statement = null;
        Author result;
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            // TODO CHECK!
            statement.executeQuery("SELECT * FROM authors WHERE id = " + id + ";");
            ResultSet resultSet = statement.getResultSet();
            result = new Author();
            while (resultSet.next()) {
                result = parseAuthors(resultSet);
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

    public Author getByName(String firstName, String lastName) throws SQLException {
        String sql = "SELECT * FROM authors WHERE first_name = ? AND last_name =?;";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);
        Author result = null;
        try {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            new Author();
            while (resultSet.next()) {
                result = parseAuthors(resultSet);
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

    private Author parseAuthors(ResultSet resultSet) throws Exception {
        Author result = new Author();

        result.setId(resultSet.getInt("id"));
        result.setFirstName(resultSet.getString("first_name"));
        result.setLastName(resultSet.getString("last_name"));
        return result;
    }

    public long addAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO authors (first_name, last_name) VALUES (?, ?);";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, author.getFirstName());
        statement.setString(2, author.getLastName());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        long last_inserted_id = 0;
        if (resultSet.next()) {
            last_inserted_id = resultSet.getLong(1);
        }
        statement.close();
        return last_inserted_id;
    }

    public Set<Author> getAuthorsByLastName(String query) throws Exception {
        Set<Author> authors = new TreeSet<>();
        String sql = "SELECT * FROM authors WHERE last_name like ?;";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);
        try {
            statement.setString(1, query + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(parseAuthors(resultSet));
            }
            statement.close();
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
        return authors;
    }
}