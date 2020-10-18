package dao;

import Additional.SQLQueriesShortcutter;
import Classes.User;
import Classes.Product;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Dao {
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;
    protected final String USERNAME = "postgres";
    protected final String PASSWORD = "duman070601";
    protected String query;
    ArrayList<Product> list = new ArrayList<>();

    protected void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/users",
                        USERNAME, PASSWORD);
    }

    public boolean checkLogin(String email, String password) {
        try {
            getConnection();
            connection.setAutoCommit(false);
            query = SQLQueriesShortcutter.select(
                    User.TABLE_NAME,
                    User.EMAIL + " = ? " + " and " + User.PASSWORD + " = ? ",
                    "*");
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            exit(0);
        } finally {
            finallyBlock(statement, connection);
        }
        return false;
    }

    public User setUserValues(User user) {
        try {
            getConnection();
            connection.setAutoCommit(false);
            query = SQLQueriesShortcutter.select(
                    User.TABLE_NAME,
                    User.EMAIL + " = ? " + " and " + User.PASSWORD + " = ? ",
                    "*");
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setJob(resultSet.getString(4));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            exit(0);
        } finally {
            finallyBlock(statement, connection);
        }
        return user;
    }

    private void finallyBlock(PreparedStatement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Product> getAllProducts() {
        try {
            getConnection();
            query = SQLQueriesShortcutter.selectAll(Product.TABLE_NAME);
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                int price = resultSet.getInt("price");
                String image = resultSet.getString("image");

                Product p = new Product();
                p.setId(id);
                p.setName(name);
                p.setCategory(category);
                p.setPrice(price);
                p.setImage(image);
                list.add(p);
                p = null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            exit(0);
        } finally {
            finallyBlock(statement, connection);
        }
        return list;
    }
}
