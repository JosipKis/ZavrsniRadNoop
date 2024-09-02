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
    private String takeOffDate;
    private JButton printToPDF;
    private JButton confirmBtn;

    private List<String> flightDetails;

    private Kontroler kontroler;

    public ReceiptPanel(List<String> flightDetails){
        this.flightDetails = flightDetails;
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        kontroler = new Kontroler();

        plane = new JTextField(flightDetails.get(0));
        takeOffCity = new JTextField(flightDetails.get(1));
        destinationCity = new JTextField(flightDetails.get(2));
        takeOffDate = flightDetails.get(3);

        printToPDF = new JButton("PDF karta");

        confirmBtn = new JButton("Potvrdi");
        confirmBtn.setPreferredSize(printToPDF.getPreferredSize());
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(new JLabel("Hvala vam na kupovini karte " + Kontroler.getCurrentUser() + "!"), gbc);
    }

    private void activateComps() {

    }
}
