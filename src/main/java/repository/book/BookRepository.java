package repository.book;

import connection.JdbcConnection;
import entity.Book;

import java.sql.*;

import static repository.book.BookQuery.*;

public class BookRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public void save(Book book) {
        try {

            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(SAVE);
                preparedStatement.setInt(1, book.getBookId());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setInt(3, book.getPublishedYear());
                preparedStatement.setInt(4, book.getAuthorId());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Book load(int bookId) {
        Book book = new Book();
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(LOAD_BY_ID);
                preparedStatement.setInt(1, bookId);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    book.setBookId(resultSet.getInt("book_id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setPublishedYear(resultSet.getInt("publishedYear"));
                    book.setAuthorId(resultSet.getInt("author_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
                JdbcConnection.closeResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return book;
    }


    public void delete(Book book) {
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(DELETE);
                preparedStatement.setInt(1, book.getBookId());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public int save1(Book book) {
        int bookId = 0;
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, book.getBookId());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setInt(3, book.getPublishedYear());
                preparedStatement.setInt(4, book.getAuthorId());
                preparedStatement.execute();
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet != null && resultSet.next()) {
                    bookId = resultSet.getInt("book_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return bookId;
    }


    public Book[] loadAll() {
        Book[] books = new Book[0];
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(LOAD_ALL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultSet = preparedStatement.executeQuery();
                int size = 0;
                resultSet.last();
                size = resultSet.getRow();
                resultSet.beforeFirst();
                books = new Book[size];
                int count = 0;
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setBookId(resultSet.getInt("book_id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setPublishedYear(resultSet.getInt("publishedYear"));
                    book.setAuthorId(resultSet.getInt("author_id"));
                    books[count++] = book;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public int loadId() {
        int bookId = 0;
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(LOAD_ALL);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    bookId = resultSet.getInt("book_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
                JdbcConnection.closeResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return bookId;
    }
}
