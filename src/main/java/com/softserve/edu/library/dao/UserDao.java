package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.dto.DeptorsDto;
import com.softserve.edu.library.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String ADD_USER = "insert into " +
            "users(first_name, last_name, date_of_birth, registration_date, username, password) " +
            "values (?, ?, ? ,?, ?, ?)";
    private final String GET_AVG_AGE = "select avg(year(curdate()) - year(date_of_birth)) as avgAge from users;";
    private final String GET_AVG_LIBRARY_USAGE = "select avg(datediff(curdate(), registration_date)) as avgUsage " +
            "from users;";
    private final String GET_AVG_COUNT_OF_APPEAL = "select count(*) as avgCount from records group by id_user;";
    private final String GET_DEPTORS = "select u.first_name, u.last_name, b.name, r.take_date from " +
            "records r join users u on r.id_user = u.id join books b on b.id = r.id_book " +
            "where curdate() - take_date >= 10 and return_date is null;";

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

    public User getUser(String username) {
        Statement statement = null;
        User resultUser;
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "';");
            ResultSet resultSet = statement.getResultSet();
            resultUser = fillUpResultUser(resultSet);
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

    public User getUser(String username, String password) {
        Statement statement = null;
        User resultUser;
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "' " +
                    "and password = '" + password + "';");
            ResultSet resultSet = statement.getResultSet();
            resultUser = fillUpResultUser(resultSet);
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

    public User fillUpResultUser(ResultSet resultSet) {
        User resultUser = new User();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;
    }

    public int getAvgAge() {
        int result = 0;
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_AVG_AGE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("avgAge");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getAvgLibraryUsage() {
        int result = 0;
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_AVG_LIBRARY_USAGE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt("avgUsage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getAvgCountOfAppeal() {
        int result = 0;
        try {
            int rowCount = 0;
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_AVG_COUNT_OF_APPEAL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result += resultSet.getInt("avgCount");
                rowCount++;
            }
            result = result / rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<DeptorsDto> getDeptors() {
        List<DeptorsDto> dtoList = new ArrayList<>();
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_DEPTORS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                dtoList.add(new DeptorsDto(resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("name"),
                        resultSet.getString("take_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtoList;
    }
}
