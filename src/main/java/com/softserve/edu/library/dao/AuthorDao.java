package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Author;

import java.sql.ResultSet;
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

    private Author parseAuthors(ResultSet resultSet) throws Exception {
        Author result = new Author();

        result.setId(resultSet.getInt("id"));
        result.setFirstName(resultSet.getString("first_name"));
        result.setLastName(resultSet.getString("last_name"));
        return result;
    }
}
