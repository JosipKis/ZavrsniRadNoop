package view;

import controller.Kontroler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Receipt4FlightFrame extends JFrame {

    private ReceiptPanel receiptPanel;
    private ReceiptButtonsPanel receiptButtonsPanel;

    private List<String> flightDetails;

    public Receipt4FlightFrame(List<String> flightDetails){
        super("Mr. KIŠ FLIGHTS");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        this.flightDetails = flightDetails;
        initComps();
        layoutComps();
        activateReceiptFrame();
    }

    private void initComps() {
        receiptPanel = new ReceiptPanel(flightDetails);
        receiptButtonsPanel = new ReceiptButtonsPanel();
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(new JLabel("Hvala vam na kupovini karte " + Kontroler.getCurrentUser() + "!"), gbc);
        gbc.gridy++;

        add(new JLabel("Podaci o letu:"), gbc);
        gbc.gridy++;

        add(receiptPanel, gbc);
        gbc.gridy++;

        add(receiptButtonsPanel, gbc);
    }

    private void activateReceiptFrame() {
        receiptButtonsPanel.setListener(new ReceiptButtonsListener() {

            @Override
            public void print2PDF() {
                System.out.println("Print to PDF");
            }

            @Override
            public void confirm() {
                System.out.println("Confirm");
            }
        });
    }
}
