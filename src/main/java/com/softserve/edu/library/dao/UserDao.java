package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.User;

import java.sql.*;

public class UserDao {

    private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/library";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private static final String ADD_USER = "insert into " +
            "users(first_name, last_name, date_of_birth, registration_date, username, password) " +
            "values (?, ?, ? ,?, ?, ?)";

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public void addUser(User user){
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(ADD_USER);
            preparedStatement.setString(1,user.getFirstName());
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

    public User getCredentialsForLogin(String userName, String password) {
        Statement statement = null;
        User result = null;
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeQuery("SELECT * FROM users WHERE username = '" + userName + "' AND password = '" +
                    password + "';");
            ResultSet resultSet = statement.getResultSet();
            result = new User();
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
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
