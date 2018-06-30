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
}
