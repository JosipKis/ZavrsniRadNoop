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

    public void setBookingListener(BookingListener bookingListener){
        userFlightFinalizationPanel.setBookingListener(bookingListener);
    }

    public void setText(String text){
        userFlightFinalizationPanel.setText(text);
    }

    public String getText(){
        return userFlightFinalizationPanel.getText();
    }

    public String getSelectedPlaneClass(){
        return userFlightOptionsPanel.getSelectedFlightClass();
    }
}
