package controller.strategy;

import java.util.List;

public class ButtonInspector extends PressedButtonAbsCLS{

    public ButtonInspector(String button) {
        this.addPlaneToDBStrategy = new AddPlaneToDBStrategy();
        this.addFlightToDBStrategy = new AddFlightToDBStrategy();
        this.addTicketToDBStrategy = new AddTicketToDBStrategy();
        this.addNewUserToDBStrategy = new AddNewUserToDBStrategy();

        switch (button){
            case "createPlane":
                setPressedButtonStrategy(addPlaneToDBStrategy);
                break;
            case "createFlight":
                setPressedButtonStrategy(addFlightToDBStrategy);
                break;
            case "book":
                setPressedButtonStrategy(addTicketToDBStrategy);
                break;
            case "register":
                setPressedButtonStrategy(addNewUserToDBStrategy);
                break;
        }
    }

    @Override
    public void pressedButton(List<String> data) {
        pressedButtonStrategy.pressedButton(data);
    }
}
