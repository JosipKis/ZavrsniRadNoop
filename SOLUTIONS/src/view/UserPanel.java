package view;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {

    private UserFlightOptionsPanel userFlightOptionsPanel;
    private UserFlightFinalizationPanel userFlightFinalizationPanel;

    public UserPanel() {
        super();
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        userFlightOptionsPanel = new UserFlightOptionsPanel();
        userFlightOptionsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        userFlightFinalizationPanel = new UserFlightFinalizationPanel();
        userFlightFinalizationPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    private void layoutComps() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(userFlightOptionsPanel);
        add(userFlightFinalizationPanel);
    }

    private void activatePanel() {

    }

    public void setText(String text){
        userFlightFinalizationPanel.setText(text);
    }
}
