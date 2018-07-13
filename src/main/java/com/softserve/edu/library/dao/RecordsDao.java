package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.dto.TakenBookDto;
import com.softserve.edu.library.entity.Book;
import com.softserve.edu.library.entity.Record;
import com.softserve.edu.library.entity.User;

import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecordsDao {

    private final String TAKE_BOOK = "INSERT INTO records (id_user, id_book, take_date) VALUES (?, ?, ?)";
    private final String DECREMENT_DO = "UPDATE books SET available = ? WHERE id = ?";
    private final String DECREMENT_AVAILABLE = "select available from books where id = ?;";
    private final String CHECK_USER_BOOK_TAKEN = "select count(*) as number from users u join records r on u.id = r.id_user where u.id = ? and id_book = ? and return_date is null;";
    private final String GET_ALL_TAKEN_BOOKS_BY_USER_ID = "select id_user, id_book, returned, take_date, return_date, `name` from records, books where id_user = ? and records.id_book = books.id";
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

    public boolean isUserAlreadyTakeThisBook(long idUser, long idBook) {
        PreparedStatement preparedStatement;
        boolean result = false;
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(CHECK_USER_BOOK_TAKEN);
            preparedStatement.setLong(1, idUser);
            preparedStatement.setLong(2, idBook);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("number") > 0) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<TakenBookDto> getAllBooksByUserId(long idUser) {
        List<TakenBookDto> allBooksByUserId;
        PreparedStatement statement = null;
        try {
            statement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_ALL_TAKEN_BOOKS_BY_USER_ID);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            allBooksByUserId = fillUpResult(resultSet);
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
        return allBooksByUserId;
    }

    public List<TakenBookDto> fillUpResult(ResultSet resultSet) {
        List<TakenBookDto> allBooksByUserId = new LinkedList<>();
        try {
            while (resultSet.next()) {
                TakenBookDto resultTakenBookDto = new TakenBookDto();
                resultTakenBookDto.setIdUser(resultSet.getLong("id_user"));
                resultTakenBookDto.setIdBook(resultSet.getLong("id_book"));
                resultTakenBookDto.setReturned(resultSet.getBoolean("returned"));
                resultTakenBookDto.setTakeDate(resultSet.getDate("take_date"));
                resultTakenBookDto.setReturnDate(resultSet.getDate("return_date"));
                resultTakenBookDto.setBookName(resultSet.getString("name"));
                allBooksByUserId.add(resultTakenBookDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooksByUserId;
    }
}
