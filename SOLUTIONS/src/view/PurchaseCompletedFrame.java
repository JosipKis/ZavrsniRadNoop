package view;

import javax.swing.*;
import java.awt.*;

public class PurchaseCompletedFrame extends JFrame {

    private PurchaseCompletedPanel purchaseCompletedPanel;

    public PurchaseCompletedFrame(){
        super("Mr.Kiš Flights");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        purchaseCompletedPanel = new PurchaseCompletedPanel();
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(purchaseCompletedPanel, BorderLayout.CENTER);
    }

    private void activateFrame() {

    }
}
