package view;

import controller.Kontroler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReceiptPanel extends JPanel {

    private JTextField plane;
    private JTextField takeOffCity;
    private JTextField destinationCity;
    private JTextField takeOffDate;
    private JTextField takeOffTime;
    private JTextField totalPaid;

    private List<String> flightDetails;

    public ReceiptPanel(List<String> flightDetails){
        this.flightDetails = flightDetails;
        initComps();
        layoutComps();
    }

    private void initComps() {

        plane = new JTextField(flightDetails.get(0));
        takeOffCity = new JTextField(flightDetails.get(1));
        destinationCity = new JTextField(flightDetails.get(2));
        takeOffDate = new JTextField(flightDetails.get(3));
        takeOffTime = new JTextField(flightDetails.get(4));
        totalPaid = new JTextField(flightDetails.get(5));

        plane.setEditable(false);
        plane.setFocusable(false);
        takeOffCity.setEditable(false);
        takeOffCity.setFocusable(false);
        destinationCity.setEditable(false);
        destinationCity.setFocusable(false);
        takeOffDate.setEditable(false);
        takeOffDate.setFocusable(false);
        takeOffTime.setEditable(false);
        takeOffTime.setFocusable(false);
        totalPaid.setEditable(false);
        totalPaid.setFocusable(false);

        plane.setPreferredSize(new Dimension(takeOffDate.getPreferredSize().width + 20, takeOffDate.getPreferredSize().height));
        takeOffCity.setPreferredSize(new Dimension(takeOffDate.getPreferredSize().width + 20, takeOffDate.getPreferredSize().height));
        destinationCity.setPreferredSize(new Dimension(takeOffDate.getPreferredSize().width + 20, takeOffDate.getPreferredSize().height));
        takeOffTime.setPreferredSize(new Dimension(takeOffDate.getPreferredSize().width + 20, takeOffDate.getPreferredSize().height));
        totalPaid.setPreferredSize(new Dimension(takeOffDate.getPreferredSize().width + 20, takeOffDate.getPreferredSize().height));
        takeOffDate.setPreferredSize(new Dimension(takeOffDate.getPreferredSize().width + 20, takeOffDate.getPreferredSize().height));

        plane.setHorizontalAlignment(JTextField.CENTER);
        takeOffCity.setHorizontalAlignment(JTextField.CENTER);
        destinationCity.setHorizontalAlignment(JTextField.CENTER);
        takeOffDate.setHorizontalAlignment(JTextField.CENTER);
        takeOffTime.setHorizontalAlignment(JTextField.CENTER);
        totalPaid.setHorizontalAlignment(JTextField.CENTER);
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(new JLabel("Avion: "), gbc);
        gbc.gridx++;

        add(plane, gbc);
        gbc.gridx--;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 3, 0);

        add(new JLabel("Polazni grad: "), gbc);
        gbc.gridx++;

        add(takeOffCity, gbc);
        gbc.gridx--;
        gbc.gridy++;

        add(new JLabel("Odredišni grad: "), gbc);
        gbc.gridx++;

        add(destinationCity, gbc);
        gbc.gridx--;
        gbc.gridy++;

        add(new JLabel("Datum polaska: "), gbc);
        gbc.gridx++;

        add(takeOffDate, gbc);
        gbc.gridx--;
        gbc.gridy++;

        add(new JLabel("Vrijeme polaska: "), gbc);
        gbc.gridx++;

        add(takeOffTime, gbc);
        gbc.gridx--;
        gbc.gridy++;

        add(new JLabel("Ukupna cijena: "), gbc);
        gbc.gridx++;

        add(totalPaid, gbc);
    }
}
