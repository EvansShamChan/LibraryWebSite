package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.User;

import java.sql.*;

public class UserDao {

    private static final String ADD_USER = "insert into " +
            "users(first_name, last_name, date_of_birth, registration_date, username, password) " +
            "values (?, ?, ? ,?, ?, ?)";

    PreparedStatement preparedStatement = null;

    public void addUser(User user) {
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, user.getDateOfBirth());
            preparedStatement.setDate(4, user.getRegistrationDate());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username, String password) {
        Statement statement = null;
        User resultUser;
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" +
                    password + "';");
            ResultSet resultSet = statement.getResultSet();
            resultUser = new User();
            while (resultSet.next()) {
                resultUser.setId(resultSet.getInt("id"));
                resultUser.setFirstName(resultSet.getString("first_name"));
                resultUser.setLastName(resultSet.getString("last_name"));
                resultUser.setDateOfBirth(resultSet.getDate("date_of_birth"));
                resultUser.setRegistrationDate(resultSet.getDate("registration_date"));
                resultUser.setUsername(resultSet.getString("username"));
                resultUser.setPassword(resultSet.getString("password"));
                resultUser.setIsAdmin(resultSet.getLong("is_admin"));
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
        return resultUser;
    }
}
