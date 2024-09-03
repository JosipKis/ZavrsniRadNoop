package model;

public class Flight {

    private int flightNumber;
    private String departure;
    private String destination;
    private String departureDate;
    private int planeID;
    private String departureTime;

    public int getFlightNumber() { return flightNumber; }

    public String getDeparture() { return departure; }

    public String getDestination() { return destination; }

    public String getDepartureDate() { return departureDate; }

    public int getPlane() { return planeID; }

    public String getDepartureTime() { return departureTime; }

    public void setFlightNumber(int flightNumber) { this.flightNumber = flightNumber; }

    public void setDeparture(String departure) { this.departure = departure; }

    public void setDestination(String destination) { this.destination = destination; }

    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }

    public void setPlane(int planeID) { this.planeID = planeID; }

    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime='" + departureDate + '\'' +
                ", planeID=" + planeID +
                ", price='" + departureTime + '\'' +
                '}';
    }
}
