package controller.strategy;

import controller.Kontroler;

import java.util.List;

public class AddTicketToDBStrategy implements  PressedButtonStrategy{

    private Kontroler kontroler;

    public AddTicketToDBStrategy() {
        kontroler = Kontroler.getInstance();
        kontroler.connectToDatabase();
    }

    @Override
    public void pressedButton(List<String> data) {
        kontroler.addTicketToDb(data);
    }
}
