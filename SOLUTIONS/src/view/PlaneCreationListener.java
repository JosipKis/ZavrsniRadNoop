package view;

import java.util.EventListener;

public interface PlaneCreationListener extends EventListener {

    void createBtnPressed();

    void checkBoxSelected(String actionCommand);
}
