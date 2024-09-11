package view;

import controller.command.Command;
import controller.command.CommandInvoker;
import controller.command.SortByEarliestCommand;
import controller.command.SortByLatestCommand;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {

    private UserFlightOptionsPanel userFlightOptionsPanel;
    private UserFlightFinalizationPanel userFlightFinalizationPanel;

    private FlightListPanel flightListPanel;

    private CommandInvoker commandInvoker;

    public UserPanel(FlightListPanel flightListPanel) {
        super();
        this.flightListPanel = flightListPanel;
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        userFlightOptionsPanel = new UserFlightOptionsPanel();
        userFlightOptionsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        userFlightOptionsPanel.activatePanel(this);

        commandInvoker = new CommandInvoker();

        userFlightFinalizationPanel = new UserFlightFinalizationPanel();
        userFlightFinalizationPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    private void layoutComps() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(userFlightOptionsPanel);
        add(userFlightFinalizationPanel);
    }

    private void activatePanel(){
        userFlightOptionsPanel.setComboBoxListener(new ComboBoxListener() {

            @Override
            public void comboBoxPressed(String selection) {
                switch (selection){
                    case "Najstarije gore" :
                        System.out.println("Sortirano s najstarijim na vrhu");
                        Command sortByEarliestCommand = new SortByEarliestCommand(flightListPanel);
                        commandInvoker.runCommand(sortByEarliestCommand);

                        break;
                    case "Najnovije gore" :
                        System.out.println("Sortirano s najnovijim na vrhu");
                        Command sortByLatestCommand = new SortByLatestCommand(flightListPanel);
                        commandInvoker.runCommand(sortByLatestCommand);

                        break;
                }
            }
        });
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