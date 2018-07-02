package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.User;

import java.sql.ResultSet;
import java.sql.Statement;

public class SignInDao {
    public User getUserByUserName(String userName, String password) {
        Statement statement = null;
        User result = null;
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeQuery("SELECT * FROM users WHERE username = '" + userName + "' AND password = '" +
                    password + "';");
            ResultSet resultSet = statement.getResultSet();
            result = new User();
            while (resultSet.next()) {
                result.setId(resultSet.getLong("id"));
                result.setFirstName(resultSet.getString("first_name"));
                result.setLastName(resultSet.getString("last_name"));
                result.setDateOfBirth(resultSet.getDate("date_of_birth"));
                result.setRegistrationDate(resultSet.getDate("registration_date"));
                result.setUsername(resultSet.getString("username"));
                result.setPassword(resultSet.getString("password"));
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