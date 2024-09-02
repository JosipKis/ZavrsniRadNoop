package view;

import javax.swing.*;
import java.util.List;

public class Receipt4FlightFrame extends JFrame {

    private ReceiptPanel receiptPanel;

    private List<String> flightDetails;

    public Receipt4FlightFrame(List<String> flightDetails){
        super("Mr. KIŠ FLIGHTS");
        setSize(700, 500);
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
    }

    private void layoutComps() {
        add(receiptPanel);
    }

    private void activateReceiptFrame() {

    }
}
