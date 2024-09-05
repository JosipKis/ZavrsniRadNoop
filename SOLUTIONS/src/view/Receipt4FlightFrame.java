package view;

import controller.Kontroler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Receipt4FlightFrame extends JFrame {

    private ReceiptPanel receiptPanel;
    private ReceiptButtonsPanel receiptButtonsPanel;

    private JLabel thankYouLabel;
    private JLabel detailsLabel;

    private List<String> flightDetails;

    Kontroler kontroler;

    public Receipt4FlightFrame(List<String> flightDetails){
        super("Mr. KIŠ FLIGHTS");
        setSize(350, 400);
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
        kontroler = new Kontroler();
        kontroler.connectToDatabase(); //maybe will need to be removed, check later

        receiptPanel = new ReceiptPanel(flightDetails);
        receiptButtonsPanel = new ReceiptButtonsPanel();

        thankYouLabel = new JLabel("Hvala vam na kupovini karte " + Kontroler.getCurrentUser() + "!");
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 20));

        detailsLabel = new JLabel("Podaci o letu:");
        detailsLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 15, 0);

        add(thankYouLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 20, 0);

        add(detailsLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

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
                int id = Kontroler.getCurrentUserID();
                kontroler.getAllTicketsByID(id);
//                dispose();
//                new UserFrame();
            }
        });
    }
}
