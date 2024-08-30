package view;

import javax.swing.*;
import java.awt.*;

public class UserFlightOptionsPanel extends JPanel {

    private JComboBox<String> flightsComboBox;
    private JCheckBox handLuggage;
    private JCheckBox checkedLuggage;

    private JRadioButton firstClass;
    private JRadioButton businessClass;
    private JRadioButton economyClass;
    private ButtonGroup buttonGroup;

    public UserFlightOptionsPanel(){
        super();
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        flightsComboBox = new JComboBox<>();
        flightsComboBox.setPreferredSize(new Dimension(100, 30));

        handLuggage = new JCheckBox("Hand Luggage");
        handLuggage.setEnabled(false);
        checkedLuggage = new JCheckBox("Checked Luggage");
        checkedLuggage.setEnabled(false);

        firstClass = new JRadioButton("First Class");
        firstClass.setActionCommand("first");
        firstClass.setEnabled(false);
        businessClass = new JRadioButton("Business Class");
        businessClass.setActionCommand("bussy");
        businessClass.setEnabled(false);
        economyClass = new JRadioButton("Economy Class");
        economyClass.setActionCommand("eco");
        economyClass.setEnabled(false);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(firstClass);
        buttonGroup.add(businessClass);
        buttonGroup.add(economyClass);
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        add(flightsComboBox, gbc);
        gbc.gridx++;
        gbc.insets = new Insets(0, 0, 0, 15);

        add(handLuggage, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 2, 0, 0);

        add(checkedLuggage, gbc);
        gbc.gridy = 0;
        gbc.gridx++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(firstClass, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 23, 5, 0);

        add(businessClass, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 27, 0, 0);

        add(economyClass, gbc);
    }

    private void activatePanel() {

    }

    public String getSelectedFlightClass(){
        return buttonGroup.getSelection().getActionCommand();
    }

    public void setEnableOptions(int first, int second, int third){
        handLuggage.setEnabled(true);
        checkedLuggage.setEnabled(true);

        if (first != 0){
            firstClass.setEnabled(true);
        }

        if (second != 0){
            businessClass.setEnabled(true);
        }

        if (third != 0){
            economyClass.setEnabled(true);
        }
        System.out.println("First: " + first + ", Second: " + second + ", Third: " + third);

    }

    public void disableOptions(){
        handLuggage.setEnabled(false);
        checkedLuggage.setEnabled(false);
        firstClass.setEnabled(false);
        businessClass.setEnabled(false);
        economyClass.setEnabled(false);
    }
}
