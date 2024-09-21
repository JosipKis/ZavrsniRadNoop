package model.strategy;

import java.util.ArrayList;
import java.util.List;

public abstract class PressedButtonAbsCLS {

    protected AddPlaneToDBStrategy addPlaneToDBStrategy;

    protected PressedButtonStrategy pressedButtonStrategy;

    public void setPressedButtonStrategy(PressedButtonStrategy pressedButtonStrategy) {
        this.pressedButtonStrategy = pressedButtonStrategy;
    }

    public abstract void pressedButton(List<String> data);
}
