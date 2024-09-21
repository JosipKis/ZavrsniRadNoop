package model.strategy;

import controller.Kontroler;

import java.util.List;

public class AddPlaneToDBStrategy implements PressedButtonStrategy {

    private Kontroler kontroler;

    public AddPlaneToDBStrategy() {
        kontroler = Kontroler.getInstance();
        kontroler.connectToDatabase();
    }

    @Override
    public void pressedButton(List<String> planeData) {
        kontroler.addPlaneToDB(planeData);
        System.out.println(planeData + " by using strategy pattern.");
        kontroler.dissconnect();
    }
}
