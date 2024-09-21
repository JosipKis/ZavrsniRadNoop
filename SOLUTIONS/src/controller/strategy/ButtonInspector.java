package controller.strategy;

import java.util.List;

public class ButtonInspector extends PressedButtonAbsCLS{

    public ButtonInspector(String button) {
        this.pressedButtonStrategy = new AddPlaneToDBStrategy();
        this.pressedButtonStrategy = new AddFlightToDBStrategy();
        this.addTicketToDBStrategy = new AddTicketToDBStrategy();
        this.addNewUserToDBStrategy = new AddNewUserToDBStrategy();

        switch (button){
            case "createPlane":
                setPressedButtonStrategy(pressedButtonStrategy);
                break;
            case "createFlight":
                setPressedButtonStrategy(new AddFlightToDBStrategy());
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
