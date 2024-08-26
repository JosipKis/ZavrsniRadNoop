package model;

public class Flight {

    private String flightNumber;
    private String departure;
    private String destination;
    private String departureTime;
    private String plane;
    private String price;

    public String getFlightNumber() { return flightNumber; }

    public String getDeparture() { return departure; }

    public String getDestination() { return destination; }

    public String getDepartureTime() { return departureTime; }

    public String getPlane() { return plane; }

    public String getPrice() { return price; }

    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public void setDeparture(String departure) { this.departure = departure; }

    public void setDestination(String destination) { this.destination = destination; }

    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public void setPlane(String plane) { this.plane = plane; }

    public void setPrice(String price) { this.price = price; }
}
