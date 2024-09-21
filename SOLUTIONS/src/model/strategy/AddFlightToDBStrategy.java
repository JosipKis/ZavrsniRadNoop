package model.strategy;

import controller.Kontroler;

import java.util.List;

public class AddFlightToDBStrategy implements PressedButtonStrategy{

    private Kontroler kontroler;

    public AddFlightToDBStrategy() {
        kontroler = Kontroler.getInstance();
        kontroler.connectToDatabase();
    }

    @Override
    public void pressedButton(List<String> data) {
        System.out.println("Flight data: " + data + " added to database.");
        kontroler.addFlightToDB(data);
    }
}
