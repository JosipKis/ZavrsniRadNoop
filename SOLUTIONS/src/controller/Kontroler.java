package controller;

import model.DataBase;
import model.User;

import java.sql.*;

public class Kontroler {

    private Connection con;

    private static final String DB_URL = "jdbc:mysql://142.93.166.77:3306/NoopZavrsni";
    private static final String DB_USER = "jkis";
    private static final String DB_PASSWORD = "noop,LOZINKA101a";

    private User currentUser;
    private String currentUserRole;
    private DataBase dataBase;

    public Kontroler() {
        dataBase = new DataBase();
    }

    public void connectToDatabase() {
        System.out.println("Connecting to database...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to -> " + con.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dissconnect(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean autehnticateLogin(String username, String password) throws SQLException {
        String SELECT_USER_QUERY = "SELECT * FROM User WHERE username = ? AND password = ?";

        PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_QUERY);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String role = resultSet.getString("role");
            currentUser = new User(username, password);
            dataBase.setCurrentUser(currentUser);
            if (role.equals("admin")){
                System.out.println("Admin");
                currentUserRole = "admin";
            } else {
                System.out.println("User");
                currentUserRole = "user";
            }

            return true;
        } else {
            System.out.println("Neuspjesna prijava!");
            return false;
        }

    }

    public void registerNewUser(String username, String password, int age) throws SQLException {
        int id = 3;
        String INSERT_USER_QUERY = "INSERT INTO User (id, username, password, age,  role) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_QUERY);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.setInt(4, age);
        preparedStatement.setString(5, "user");

        preparedStatement.executeUpdate();
    }

    public String getUserRole(){
        return currentUserRole;
    }
}
