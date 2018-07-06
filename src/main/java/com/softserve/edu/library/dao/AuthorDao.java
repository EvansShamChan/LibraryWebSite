package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
