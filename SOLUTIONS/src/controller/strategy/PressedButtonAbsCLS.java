package controller.strategy;

import java.util.List;

public abstract class PressedButtonAbsCLS {

    protected AddPlaneToDBStrategy addPlaneToDBStrategy;
    protected AddFlightToDBStrategy addFlightToDBStrategy;
    protected AddTicketToDBStrategy addTicketToDBStrategy;
    protected AddNewUserToDBStrategy addNewUserToDBStrategy;

    protected PressedButtonStrategy pressedButtonStrategy;

    public void setPressedButtonStrategy(PressedButtonStrategy pressedButtonStrategy) {
        this.pressedButtonStrategy = pressedButtonStrategy;
    }

    public abstract void pressedButton(List<String> data);
}
