package controller;

import model.DataBase;
import model.Flight;
import model.Ticket;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Kontroler {

    private Connection con;

    private static final String DB_URL = "jdbc:mysql://142.93.166.77:3306/NoopZavrsni";
    private static final String DB_USER = "jkis";
    private static final String DB_PASSWORD = "noop,LOZINKA101a";

    private static User currentUser;
    private String currentUserRole;
    private DataBase dataBase;
    private static List<Integer> classPrices;

    public Kontroler() {
        dataBase = new DataBase();
        classPrices = new ArrayList<>();
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
            currentUser.setId(resultSet.getInt("id"));
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

    public void registerNewUser(String username, String password, int age){
        try{
            String INSERT_USER_QUERY = "INSERT INTO User (username, password, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_QUERY);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Flight> getAllFlights(){
        if (con != null){
            String dbQuery = "SELECT * FROM Flights";
            try (PreparedStatement selectStm = con.prepareStatement(dbQuery)) {
                try (ResultSet resultSet = selectStm.executeQuery()) {
                    while (resultSet.next()) {
                        int flightID = resultSet.getInt("id");
                        int flightPlane = resultSet.getInt("plane");
                        String departure = resultSet.getString("departure");
                        String destination = resultSet.getString("destination");
                        Date date = resultSet.getDate("departure_time");
                        Time time = resultSet.getTime("departure_time");

                        Flight flight = new Flight();
                        flight.setFlightNumber(flightID);
                        flight.setPlane(flightPlane);
                        flight.setDeparture(departure);
                        flight.setDestination(destination);
                        flight.setDepartureDate(String.valueOf(date));
                        flight.setDepartureTime(String.valueOf(time));

                        dataBase.getFlightsFromDB(flight);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dataBase.getFlights();
    }

    public String getPlaneNameByID(int id) {
        if (con != null) {
            String query = "SELECT Plane.name FROM Plane WHERE id = ?;";
            try (PreparedStatement selectStm = con.prepareStatement(query)) {
                selectStm.setInt(1, id);
                try (ResultSet resultSet = selectStm.executeQuery()) {
                    while (resultSet.next()){
                        String name = resultSet.getString("name");

                        dataBase.setPlaneName(name);
                    }
                }

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return dataBase.returnPlaneName();
    }

    public List<Integer> getPlaneClasses(String name){
        if (con != null) {
            System.out.println(name);
            String query = "SELECT * FROM Plane WHERE name = ?;";
            System.out.println("query passes");
            try (PreparedStatement selectStm = con.prepareStatement(query)) {
                System.out.println("1st");
                selectStm.setString(1, name);
                try (ResultSet resultSet = selectStm.executeQuery()) {
                    System.out.println("2nd");
                    while (resultSet.next()){
                        dataBase.resetPlaneClasses();
                        classPrices.clear();

                        int isFirstClass = resultSet.getInt("firstClass");
                        int isBusinessClass = resultSet.getInt("businessClass");
                        int isEconomy = resultSet.getInt("economyClass");

                        dataBase.addPlaneClasses(isFirstClass);
                        dataBase.addPlaneClasses(isBusinessClass);
                        dataBase.addPlaneClasses(isEconomy);

                        classPrices.add(isFirstClass);
                        classPrices.add(isBusinessClass);
                        classPrices.add(isEconomy);
                    }
                }

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return dataBase.getChosenPlaneClasses();
    }

    public List<Ticket> getAllTicketsByID(int id){
        dataBase.resetTicketsList();
        if (con != null) {
            String query = "SELECT * FROM ticket WHERE userID = ?;";

            try (PreparedStatement ps = con.prepareStatement(query)){
                ps.setInt(1, id);

                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()){

                        Ticket ticket = new Ticket();

                        ticket.setId(resultSet.getInt("id"));
                        ticket.setUserId(resultSet.getInt("userID"));
                        ticket.setPlane(resultSet.getString("plane"));
                        ticket.setTakeOffCity(resultSet.getString("takeOffCity"));
                        ticket.setDestinationCity(resultSet.getString("destinationCity"));
                        ticket.setStartDate(resultSet.getString("startDate"));
                        ticket.setStartTime(resultSet.getString("startTime"));
                        ticket.setTotalPrice(resultSet.getString("totalPrice"));

                        dataBase.addTickets4User(ticket);
                        System.out.println(ticket.toString());
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dataBase.getTicketsByUser();
    }

    public void addTicketToDb(List<String> ticketDetails){
        if (con != null) {
            try {
                String query = "INSERT INTO ticket (userID, plane, takeOffCity, destinationCity, startDate, startTime, totalPrice) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, String.valueOf(getCurrentUserID()));
                ps.setString(2, ticketDetails.get(0));
                ps.setString(3, ticketDetails.get(1));
                ps.setString(4, ticketDetails.get(2));
                ps.setString(5, ticketDetails.get(3));
                ps.setString(6, ticketDetails.get(4));
                ps.setString(7, ticketDetails.get(5));

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getCurrentUser() {
        return currentUser.getUsername();
    }

    public static int getCurrentUserID() {
        return currentUser.getId();
    }

    public String getUserRole(){
        return currentUserRole;
    }

    public static List<Integer> getClassPrices() {
        return classPrices;
    }
}