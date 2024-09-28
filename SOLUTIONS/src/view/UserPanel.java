package view;

import model.observer.ObservablePanelCLS;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {

    private UserFlightOptionsPanel userFlightOptionsPanel;
    private UserFlightFinalizationPanel userFlightFinalizationPanel;

    private ObservablePanelCLS observablePanelCLS;

    public UserPanel(FlightListPanel flightListPanel) {
        super();
        initComps();
        layoutComps();
    }

    private void initComps() {
        observablePanelCLS = ObservablePanelCLS.getInstance();

        userFlightOptionsPanel = new UserFlightOptionsPanel();
        userFlightOptionsPanel.activatePanel(this);

        userFlightFinalizationPanel = new UserFlightFinalizationPanel();
        userFlightFinalizationPanel.registerObserver(observablePanelCLS);

        observablePanelCLS.printObservers();
    }

    private void layoutComps() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(userFlightOptionsPanel);
        add(userFlightFinalizationPanel);
    }

    public void setComboBoxListener(ComboBoxListener comboBoxListener) {
        userFlightOptionsPanel.setComboBoxListener(comboBoxListener);
    }

    public void setBookingListener(SingleButtonOnPanelListener singleButtonOnPanelListener){
        userFlightFinalizationPanel.setBookingListener(singleButtonOnPanelListener);
    }

    public void setText(String text){
        userFlightFinalizationPanel.setText(text);
    }

    public String getText(){
        return userFlightFinalizationPanel.getText();
    }

    public void setTotalPriceText(String totalPriceText){
        userFlightFinalizationPanel.setTotalPrice(totalPriceText);
    }

    public String getTotalPriceText(){
        return userFlightFinalizationPanel.getTotalPrice();
    }

    public void enableSelection(int first, int second, int third){
        userFlightOptionsPanel.setEnableOptions(first, second, third);
        userFlightFinalizationPanel.setTotalPrice("0 €");
        userFlightOptionsPanel.setTotalMoneyToPay(0);
    }

    public void disableOptions(){
        userFlightOptionsPanel.disableOptions();
    }

    public boolean isAClassSelected(){
        return userFlightOptionsPanel.getIsAClassSelected();
    }
}