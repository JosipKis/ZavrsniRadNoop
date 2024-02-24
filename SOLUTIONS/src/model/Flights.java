package model;

import java.util.concurrent.ThreadLocalRandom;

public class Flights implements  FlightsINT{

    private String plane;
    private String destination;
    private float price;
    private String id;

    public Flights(String plane, String destination, float price){
        this.plane = plane;
        this.destination = destination;
        this.price = price;
        id = idGenerator();
    }

    private String idGenerator(){
        String generatedId = "";
        String[] letters = {"A", "B", "C", "D", "E", "F", "Z", "1", "2","3","4","5","6","7","8","9","0"};
        for (int c = 0; c < 10; c++){
            int chosenOne = ThreadLocalRandom.current().nextInt(letters.length);
            generatedId += letters[chosenOne];
        }

        return generatedId;
    }

    @Override
    public String getPlane() {
        return plane;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Flight_" + id;
    }
}
