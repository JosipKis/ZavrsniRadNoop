package view;

import java.util.ArrayList;

public class TicketFinalPriceCalculator {

    private ArrayList<String> baggageSelection;
    private float baggagePrice;

    private float flightClassPrice;

    private float totalPrice;

    public TicketFinalPriceCalculator(float planeReservationCost, String flightClass, ArrayList<String> baggageSelection){
        this.baggageSelection = baggageSelection;
        calculateBaggagePrice();
        calculateFlightClassPrice(flightClass);
        calculateTotalPrice(planeReservationCost);
    }

    private void calculateBaggagePrice(){
        baggagePrice = 0f;
        try {
            for (String baggageType : baggageSelection){
                switch (baggageType) {
                    case "handBaggageCb" -> baggagePrice += 15f;
                    case "cargoBg" -> baggagePrice += 45f;
                    case "addBaggage" -> baggagePrice += 30f;
                    default -> baggagePrice += 0f;
                }
            }
        }catch (NullPointerException npe){

        }

    }

    private void calculateFlightClassPrice(String flightClass){
        flightClassPrice = 0f;
        try {
            switch (flightClass) {
                case "Economy" -> flightClassPrice += 150f;
                case "Business" -> flightClassPrice += 600f;
                case "First" -> flightClassPrice += 3000f;
            }
        }catch (NullPointerException npe){

        }
    }

    private void calculateTotalPrice(float reservation){
        totalPrice = reservation + baggagePrice + flightClassPrice;

    }

    public String getTotalPrice() {
        return totalPrice + "€";
    }
}
