package model;

public class Flight {

    private int flightNumber;
    private String departure;
    private String destination;
    private String departureTime;
    private int planeID;
    private String price;

    public int getFlightNumber() { return flightNumber; }

    public String getDeparture() { return departure; }

    public String getDestination() { return destination; }

    public String getDepartureTime() { return departureTime; }

    public int getPlane() { return planeID; }

    public String getPrice() { return price; }

    public void setFlightNumber(int flightNumber) { this.flightNumber = flightNumber; }

    public void setDeparture(String departure) { this.departure = departure; }

    public void setDestination(String destination) { this.destination = destination; }

    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public void setPlane(int planeID) { this.planeID = planeID; }

    public void setPrice(String price) { this.price = price; }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", planeID=" + planeID +
                ", price='" + price + '\'' +
                '}';
    }
}
