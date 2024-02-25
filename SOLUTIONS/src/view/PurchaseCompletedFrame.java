package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PurchaseCompletedFrame extends JFrame {

    private PurchaseCompletedPanel purchaseCompletedPanel;
    private ArrayList<String> dataList;

    public PurchaseCompletedFrame(ArrayList<String> dataList){
        super("Mr.Kiš Flights");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.dataList = dataList;
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        purchaseCompletedPanel = new PurchaseCompletedPanel(dataList);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(purchaseCompletedPanel, BorderLayout.CENTER);
    }

    private void activateFrame() {
        purchaseCompletedPanel.setPurchaseCompletedListener(new PurchaseCompletedListener() {
            @Override
            public void btnPressed(String btnPressed) {
                if (btnPressed.equals("pdf")){
                    System.out.println("Printing ticket as a PDF file!");

                } else if (btnPressed.equals("refund")) {
                    System.out.println("Ticket refunded...");
                    dispose();
                    new TicketPurchaseFrame();
                }
            }
        });
    }
}
