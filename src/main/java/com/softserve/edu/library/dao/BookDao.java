package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Author;
import com.softserve.edu.library.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private AuthorToBookDao authorToBookDao = new AuthorToBookDao();
    PreparedStatement preparedStatement = null;

    private final String GET_NUMBER_OF_BOOKS = "select count(*) as number from books";
    private final String GET_BOOKS_BY_BOOK_NAME = "select * from books where name like ? limit ?, ?;";

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
                result = parseBooks(resultSet);
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

    public List<Book> getAllBooks() {
        Statement statement = null;
        List<Book> result = new ArrayList<>();
        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            // TODO CHECK!
            statement.executeQuery("SELECT * FROM books");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                result.add(parseBooks(resultSet));
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

    private Book parseBooks(ResultSet resultSet) throws Exception {
        Book result = new Book();

        result.setId(resultSet.getInt("id"));
        result.setName(resultSet.getString("name"));
        result.setPublicationDate(resultSet.getString("publication_date"));
        result.setAvailable(resultSet.getInt("available"));
        result.setAuthors(authorToBookDao.getAuthorsByBookId(String.valueOf(resultSet.getInt("id"))));

        return result;
    }

    public int getNumberOfBooks(){
        int numberOfRows = 0;
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_NUMBER_OF_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                numberOfRows = resultSet.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfRows;
    }

    public List<Book> getBookByKey(String searchKey, int start, int rowsPerPage) {
        List<Book> bookList = new ArrayList<>();
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_BOOKS_BY_BOOK_NAME);
            preparedStatement.setString(1, "%" + searchKey + "%");
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, rowsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String publication_date = resultSet.getString("publication_date");
                int available = resultSet.getInt("available");
                List<Author> authorList = authorToBookDao.getAuthorsByBookId(String.valueOf(id));
                bookList.add(new Book(id, name, publication_date, available, authorList));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bookList);
        return bookList;
    }
}


