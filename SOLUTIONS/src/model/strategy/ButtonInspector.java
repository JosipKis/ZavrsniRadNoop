package model.strategy;

import java.util.List;

public class ButtonInspector extends PressedButtonAbsCLS{

    public ButtonInspector(String button) {
        this.pressedButtonStrategy = new AddPlaneToDBStrategy();
        this.pressedButtonStrategy = new AddFlightToDBStrategy();

        switch (button){
            case "createPlane":
                setPressedButtonStrategy(pressedButtonStrategy);
                break;
            case "createFlight":
                setPressedButtonStrategy(new AddFlightToDBStrategy());
                break;
        }
    }

    @Override
    public void pressedButton(List<String> data) {
        pressedButtonStrategy.pressedButton(data);
    }
}
