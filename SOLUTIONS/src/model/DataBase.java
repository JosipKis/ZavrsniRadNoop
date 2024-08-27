package model;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private User currentUser;
    private List<Flight> flights;

    public DataBase() {
        flights = new ArrayList<>();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void getFlightsFromDB(Flight flight) {
        if (flight != null && !flights.contains(flight)) {
            flights.add(flight);
            System.out.println("Let uspješno učitan!");
        }else {
            System.out.println("Greška pri učitavanju  -  let je već unesen ili je null");
        }
    }
}
