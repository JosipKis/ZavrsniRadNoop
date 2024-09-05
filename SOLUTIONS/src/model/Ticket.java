package model;

public class Ticket {

    private int id;
    private int userId;
    private String plane;
    private String takeOffCity;
    private String destinationCity;
    private String startDate;
    private String startTime;
    private String totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getTakeOffCity() {
        return takeOffCity;
    }

    public void setTakeOffCity(String takeOffCity) {
        this.takeOffCity = takeOffCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", plane='" + plane + '\'' +
                ", takeOffCity='" + takeOffCity + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}