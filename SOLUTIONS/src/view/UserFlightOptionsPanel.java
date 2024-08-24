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
        checkedLuggage = new JCheckBox("Checked Luggage");

        firstClass = new JRadioButton("First Class");
        firstClass.setSelected(true);
        businessClass = new JRadioButton("Business Class");
        economyClass = new JRadioButton("Economy Class");

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
}
