package model.strategy;

import java.util.List;

public class ButtonInspector extends PressedButtonAbsCLS{

    public ButtonInspector(String button) {
        this.pressedButtonStrategy = new AddPlaneToDBStrategy();

        switch (button){
            case "createPlane":
                setPressedButtonStrategy(pressedButtonStrategy);
                break;
        }
    }

    @Override
    public void pressedButton(List<String> data) {
        pressedButtonStrategy.pressedButton(data);
    }
}
