package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordsDao {

    private final String TAKE_BOOK = "INSERT INTO records (id_user, id_book, take_date) VALUES (?, ?, ?)";
    private final String DECREMENT_DO = "UPDATE books SET available = ? WHERE id = ?";
    private final String DECREMENT_AVAILABLE = "select available from books where id = ?;";

    public boolean takeBook(long idUser, long idBook, java.sql.Date takeDate) {
        PreparedStatement statement = null;
        boolean rowInserted;
        try {
            statement = ConnectionManager.getInstance().getConnection().prepareStatement(TAKE_BOOK);
            statement.setLong(1, idUser);
            statement.setLong(2, idBook);
            statement.setDate(3, takeDate);

            rowInserted = statement.executeUpdate() > 0;

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
        return rowInserted;
    }


    public boolean doDecrement(long idBook, long available) {
        boolean rowInserted;
        PreparedStatement statement = null;
        try {
        statement = ConnectionManager.getInstance().getConnection().prepareStatement(DECREMENT_DO);
        statement.setLong(1, (available - 1));
        statement.setLong(2, idBook);

        rowInserted = statement.executeUpdate() > 0;

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
        return rowInserted;
    }

    public boolean isDecrementAvailable(long idBook) {
        PreparedStatement preparedStatement;
        boolean result = false;
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(DECREMENT_AVAILABLE);
            preparedStatement.setLong(1, idBook);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(Integer.parseInt(resultSet.getString("available")) > 0) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
