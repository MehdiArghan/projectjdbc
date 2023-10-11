package repository.author;

import connection.JdbcConnection;
import entity.Author;

import java.sql.*;

import static repository.author.AuthorQuery.*;

public class AuthorRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public void save(Author author) {
        try {

            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(SAVE);
                preparedStatement.setInt(1, author.getAuthorId());
                preparedStatement.setString(2, author.getFirstName());
                preparedStatement.setString(3, author.getLastName());
                preparedStatement.setInt(4, author.getAge());
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


    public Author load(int AuthorId) {

        Author author = new Author();

        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(LOAD_BY_ID);
                preparedStatement.setInt(1, AuthorId);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    author.setAuthorId(resultSet.getInt("author_id"));
                    author.setFirstName(resultSet.getString("firstname"));
                    author.setLastName(resultSet.getString("lastname"));
                    author.setAge(resultSet.getInt("age"));
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
        return author;
    }

    public int save1(Author author) {
        int authorId = 0;
        try {

            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, author.getAuthorId());
                preparedStatement.setString(2, author.getFirstName());
                preparedStatement.setString(3, author.getLastName());
                preparedStatement.setInt(4, author.getAge());
                preparedStatement.execute();
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet != null && resultSet.next()) {
                    authorId = resultSet.getInt("author_id");
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
        return authorId;
    }


    public Author[] loadAll() {
        Author[] authors = new Author[0];
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
                authors = new Author[size];
                int count = 0;
                while (resultSet.next()) {
                    Author author = new Author();
                    author.setAuthorId(resultSet.getInt("author_id"));
                    author.setFirstName(resultSet.getString("firstname"));
                    author.setLastName(resultSet.getString("lastname"));
                    author.setAge(resultSet.getInt("age"));
                    authors[count++] = author;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }


    public int loadId() {
        int authorId = 0;
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("connection is null");
            } else {
                preparedStatement = connection.prepareStatement(LOAD_ALL);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    authorId = resultSet.getInt("author_id");
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
        return authorId;
    }
}
