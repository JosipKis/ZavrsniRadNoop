package model;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private User currentUser;
    private List<Flight> flights;
    private String planeName;
    private List<Integer> chosenPlaneClasses;
    private List<Ticket> ticketsByUser;

    public DataBase() {
        flights = new ArrayList<>();
        chosenPlaneClasses = new ArrayList<>();
        ticketsByUser = new ArrayList<>();
        planeName = "";
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

    public void setPlaneName(String planeName){
        this.planeName = planeName;
    }

    public String returnPlaneName(){
        return planeName;
    }

    public void addPlaneClasses(int cls){
        chosenPlaneClasses.add(cls);
    }

    public void resetPlaneClasses(){
        chosenPlaneClasses.clear();
    }

    public List<Integer> getChosenPlaneClasses(){
        return chosenPlaneClasses;
    }

    public List<Ticket> getTicketsByUser() {
        return ticketsByUser;
    }

    public void resetTicketsList(){
        ticketsByUser.clear();
    }

    public void addTickets4User(Ticket ticket) {
        ticketsByUser.add(ticket);
    }
}
