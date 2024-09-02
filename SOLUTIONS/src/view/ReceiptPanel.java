package view;

import controller.Kontroler;
import model.Flight;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReceiptPanel extends JPanel {

    private JTextField plane;
    private JTextField takeOffCity;
    private JTextField destinationCity;
    private JTextField takeOffDate;

    private List<String> flightDetails;

    private Kontroler kontroler;

    public ReceiptPanel(List<String> flightDetails){
        this.flightDetails = flightDetails;
        initComps();
        layoutComps();
    }

    private void initComps() {
        kontroler = new Kontroler();

        plane = new JTextField(flightDetails.get(0));
        takeOffCity = new JTextField(flightDetails.get(1));
        destinationCity = new JTextField(flightDetails.get(2));
        takeOffDate = new JTextField(flightDetails.get(3));

    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(new JLabel("Podaci o letu:"), gbc);
        gbc.gridy++;

        add(new JLabel("Avion: "), gbc);
        gbc.gridx++;

        add(plane, gbc);
        gbc.gridx--;
        gbc.gridy++;

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
    }
}
