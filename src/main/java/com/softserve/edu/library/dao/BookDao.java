package com.softserve.edu.library.dao;

import com.softserve.edu.library.db.ConnectionManager;
import com.softserve.edu.library.entity.Author;
import com.softserve.edu.library.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class BookDao {
    private AuthorToBookDao authorToBookDao = new AuthorToBookDao();
    PreparedStatement preparedStatement = null;

    private final String GET_BOOKS_BY_BOOK_NAME = "select * from books where name like ? limit ?, ?;";
    private final String GET_BOOKS_BY_AUTHOR =
            "select * from books where id in(select id_book from authors_to_books where id_author " +
                    "in(select id from authors where first_name like ? and last_name like ?)) limit ?, ?;";
    private final String GET_NUMBER_OF_BOOKS_BY_NAME = "select count(*) as number from books where name like ?;";
    private final String GET_NUMBER_OF_BOOKS_BY_DATE = "select count(*) as number from books where publication_date between ? and ?;;";
    private final String GET_BOOKS_BETWEEN_DATE = "select * from books where publication_date between ? and ? limit ?, ?;";
    private final String GET_BOOKS_LESS_THAN_DATE = "select * from books where publication_date <= ? limit ?, ?;";
    private final String GET_BOOKS_BIGGER_THAN_DATE = "select * from books where publication_date >= ? limit ?, ?;";
    private final String GET_BOOKS_BY_DATE = "select * from books where publication_date = ? limit ?, ?;";
    private final String GET_BOOK_NUMBER_OF_TAKEN = "select count(*) as number from records where id_book in(select id from books where name = ?);";
    private final String GET_ALL_BOOKS = "select * from books limit ?, ?";

    public Book getByName(String name) {
        PreparedStatement statement = null;
        Book result = null;
        try {
            statement = ConnectionManager.getInstance().getConnection().prepareStatement("SELECT * FROM books WHERE name = ?;");

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

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

    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (name, publication_date, available) VALUES (?, ?, ?)";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1, book.getName());
        statement.setString(2, book.getPublicationDate());
        statement.setLong(3, book.getAvailable());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        return rowInserted;
    }

    public boolean deleteBook(String name) throws SQLException {
        String sql = "DELETE FROM books where name = ?";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1, name);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET publication_date = ?, available = ? WHERE name = ?";
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1, book.getPublicationDate());
        statement.setLong(2, book.getAvailable());
        statement.setString(3, book.getName());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        return rowUpdated;
    }

    private Book parseBooks(ResultSet resultSet) throws Exception {
        Book result = new Book();

        result.setId(resultSet.getInt("id"));
        result.setName(resultSet.getString("name"));
        result.setPublicationDate(resultSet.getString("publication_date"));
        result.setAvailable(resultSet.getLong("available"));
        result.setAuthors(authorToBookDao.getAuthorsByBookId(String.valueOf(resultSet.getInt("id"))));

        return result;
    }

    //used in all cases when search by date is not selected
    public int getNumberOfBooksByName(String searchKey) {
        int numberOfRows = 0;
        try {
            preparedStatement = ConnectionManager.getInstance()
                    .getConnection().prepareStatement(GET_NUMBER_OF_BOOKS_BY_NAME);
            preparedStatement.setString(1, "%" + searchKey + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numberOfRows = resultSet.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfRows;
    }

    //only used when search by publication date and search key in not empty
    public int getNumberOfBooksByDate(String startDate, String endDate) {
        int resultNumber = 0;
        try {
            preparedStatement = ConnectionManager.getInstance()
                    .getConnection().prepareStatement(GET_NUMBER_OF_BOOKS_BY_DATE);
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                resultNumber = resultSet.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultNumber;
    }

    public List<Book> getBookByKey(String searchKey, int start, int rowsPerPage) {
        List<Book> bookList = new ArrayList<>();
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_BOOKS_BY_BOOK_NAME);
            preparedStatement.setString(1, "%" + searchKey + "%");
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, rowsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            bookList = putValuesFromRSToBookEntity(resultSet);
            getBookNumberOfTaken(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void getBookNumberOfTaken(List<Book> bookList) {
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_BOOK_NUMBER_OF_TAKEN);
            for (Book book : bookList) {
                preparedStatement.setString(1, book.getName());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    book.setNumberOfTaken(resultSet.getInt("number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooksByAuthor(String firstname, String lastname, int start, int rowsPerPage) {
        List<Book> bookList = new ArrayList<>();
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_BOOKS_BY_AUTHOR);
            preparedStatement.setString(1, "%" + firstname + "%");
            preparedStatement.setString(2, "%" + lastname + "%");
            preparedStatement.setInt(3, start);
            preparedStatement.setInt(4, rowsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            bookList = putValuesFromRSToBookEntity(resultSet);
            getBookNumberOfTaken(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    //get all books and filter by dates
    public List<Book> getBooksByDatePeriod(String startYear, String endYear, int start, int rowsPerPage) {
        List<Book> bookList = new ArrayList<>();
        try {
            prepareBeforeBookDatePeriodSearch(startYear, endYear, start, rowsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            bookList = putValuesFromRSToBookEntity(resultSet);
            getBookNumberOfTaken(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<Book> getBooksByDate(String date, int start, int rowsPerPage) {
        List<Book> bookList = new ArrayList<>();
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_BOOKS_BY_DATE);
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, rowsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            bookList = putValuesFromRSToBookEntity(resultSet);
            getBookNumberOfTaken(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<Book> getAllBooks(int start, int rowsPerPage) {
        List<Book> bookList = new ArrayList<>();
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_ALL_BOOKS);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, rowsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            bookList = putValuesFromRSToBookEntity(resultSet);
            getBookNumberOfTaken(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    private void prepareBeforeBookDatePeriodSearch(String startYear, String endYear, int start, int rowsPerPage) {
        try{
            if (endYear.equals("")){
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_BOOKS_BIGGER_THAN_DATE);
                preparedStatement.setString(1, startYear);
                preparedStatement.setInt(2, start);
                preparedStatement.setInt(3, rowsPerPage);
            } else if(startYear.equals("")) {
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_BOOKS_LESS_THAN_DATE);
                preparedStatement.setString(1, endYear);
                preparedStatement.setInt(2, start);
                preparedStatement.setInt(3, rowsPerPage);
            } else {
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(GET_BOOKS_BETWEEN_DATE);
                preparedStatement.setString(1, startYear);
                preparedStatement.setString(2, endYear);
                preparedStatement.setInt(3, start);
                preparedStatement.setInt(4, rowsPerPage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Book> putValuesFromRSToBookEntity(ResultSet resultSet) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String publication_date = resultSet.getString("publication_date");
            int available = resultSet.getInt("available");
            List<Author> authorList = authorToBookDao.getAuthorsByBookId(String.valueOf(id));
            bookList.add(new Book(id, name, publication_date, available, authorList));
        }
        return bookList;
    }
}


