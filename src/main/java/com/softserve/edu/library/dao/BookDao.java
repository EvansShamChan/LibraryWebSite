package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Book;

import java.sql.ResultSet;
import java.sql.Statement;

public class BookDao {
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
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setPublicationDate(resultSet.getString("publication_date"));
                result.setAvailable(resultSet.getInt("available"));
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
}


