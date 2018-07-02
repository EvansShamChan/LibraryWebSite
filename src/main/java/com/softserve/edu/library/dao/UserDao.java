package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.User;
import com.softserve.edu.library.enumForProject.EnumForSqlMethod;
import com.softserve.edu.library.enumForProject.EnumForUserEntityFields;

import java.sql.*;
import java.util.Map;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class UserDao {

//    private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/library";
//    private final String USERNAME = "root";
//    private final String PASSWORD = "root";

    public User findUserInDBNew(EnumForSqlMethod sqlMethod, Map<EnumForUserEntityFields, String> map) {
        Statement statement = null;
        User user = null;
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            if (sqlMethod.equals(EnumForSqlMethod.SELECT)) {
                statement.executeQuery(buildQueryBySqlMethod(sqlMethod, map));
            } else if (sqlMethod.equals(EnumForSqlMethod.DELETE)) {
                statement.execute(buildQueryBySqlMethod(sqlMethod, map));
            } else if (sqlMethod.equals(EnumForSqlMethod.INSERT)) {
                //todo
            } else {
                //todo
            }




            if (!(sqlMethod.equals(EnumForSqlMethod.DELETE))) {
                ResultSet resultSet = statement.getResultSet();
                user = buildUserNew(resultSet);
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
        return user;
    }

    private String buildQueryBySqlMethod(EnumForSqlMethod sqlMethod, Map<EnumForUserEntityFields, String> map) {
        String query = null;

        if (sqlMethod.equals(EnumForSqlMethod.SELECT)) {
            query = "SELECT * FROM users";
            if (map.size() == 0) {
                query += ";";
                return query;
            } else {
                query += " WHERE ";
                query = conditionsForSelectAndDelete(sqlMethod, map, query);
            }

        } else if (sqlMethod.equals(EnumForSqlMethod.DELETE)) {
            if (map.size() == 0) {
                throw new RuntimeException("CAN`T DELETE ALL USERS");
            } else {
                query = "DELETE FROM users WHERE ";
                query = conditionsForSelectAndDelete(sqlMethod, map, query);
            }
        } else if (sqlMethod.equals(EnumForSqlMethod.INSERT)) {
            if (map.size() == 0) {
                throw new RuntimeException("CAN`T ADD EMPTY USER");
            } else {
                query = "INSERT INTO users (";
                query = conditionsForInsert(sqlMethod, map, query);
            }
        } else if (sqlMethod.equals(EnumForSqlMethod.UPDATE)) {
            //DOTO
        }
        System.out.println(query);
        return query;
    }

    private String conditionsForInsert(EnumForSqlMethod sqlMethod, Map<EnumForUserEntityFields, String> map, String query) {
        if (sqlMethod.equals(EnumForSqlMethod.INSERT)) {
            int counter = 0;
            String values = ") VALUES (";
            for (Map.Entry<EnumForUserEntityFields, String> entry : map.entrySet()) {
                if (entry.getKey().equals(EnumForUserEntityFields.ID)) {
                    query += "id";
                    values += "'" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += ", ";
                        values += ", ";
                    } else {
                        values += ");";
                        query += values;
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.FIRSTNAME)) {
                    query += "first_name";
                    values += "'" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += ", ";
                        values += ", ";
                    } else {
                        values += ");";
                        query += values;
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.LASTNAME)) {
                    query += "last_name";
                    values += "'" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += ", ";
                        values += ", ";
                    } else {
                        values += ");";
                        query += values;
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.DATEOFBIRTH)) {
                    query += "date_of_birth";
                    values += "'" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += ", ";
                        values += ", ";
                    } else {
                        values += ");";
                        query += values;
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.REGISTRATIONDATE)) {
                    query += "registration_date";
                    values += "'" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += ", ";
                        values += ", ";
                    } else {
                        values += ");";
                        query += values;
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.USERNAME)) {
                    query += "username";
                    values += "'" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += ", ";
                        values += ", ";
                    } else {
                        values += ");";
                        query += values;
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.PASSWORD)) {
                    query += "password";
                    values += "'" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += ", ";
                        values += ", ";
                    } else {
                        values += ");";
                        query += values;
                        break;
                    }
                }
            }
        } else {
            throw new RuntimeException("Wrong Method this is insert method");
        }
        return query;
    }

    private String conditionsForSelectAndDelete(EnumForSqlMethod sqlMethod, Map<EnumForUserEntityFields, String> map, String query) {
        if (sqlMethod.equals(EnumForSqlMethod.SELECT) || sqlMethod.equals(EnumForSqlMethod.DELETE)) {
            int counter = 0;
            for (Map.Entry<EnumForUserEntityFields, String> entry : map.entrySet()) {
                if (entry.getKey().equals(EnumForUserEntityFields.ID)) {
                    query += "id = '" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += " AND ";
                    } else {
                        query += ";";
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.FIRSTNAME)) {
                    query += "first_name = '" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += " AND ";
                    } else {
                        query += ";";
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.LASTNAME)) {
                    query += "last_name = '" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += " AND ";
                    } else {
                        query += ";";
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.DATEOFBIRTH)) {
                    query += "date_of_birth = '" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += " AND ";
                    } else {
                        query += ";";
                        return query;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.REGISTRATIONDATE)) {
                    query += "registration_date = '" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += " AND ";
                    } else {
                        query += ";";
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.USERNAME)) {

                    query += "username = '" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += " AND ";
                    } else {
                        query += ";";
                        break;
                    }
                    continue;
                }

                if (entry.getKey().equals(EnumForUserEntityFields.PASSWORD)) {

                    query += "password = '" + entry.getValue() + "'";
                    counter++;
                    if (counter < map.size()) {
                        query += " AND ";
                    } else {
                        query += ";";
                        break;
                    }
                }
            }
        } else {
            throw new RuntimeException("WRONG METHOD, THIS METHOD ONLY FOR SELECTING AND DELETING");
        }
        return query;
    }

    public User buildUserNew(ResultSet resultSet) {
        User user = new User();
        try {
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setDateOfBirth(resultSet.getDate("date_of_birth"));
                user.setRegistrationDate(resultSet.getDate("registration_date"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }






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
