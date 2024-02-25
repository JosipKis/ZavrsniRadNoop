package view.AdministrationFramePckg;

import javax.swing.*;
import java.awt.*;

public class AdministrationFrame extends JFrame {

    private AdminPanel adminPanel;
    private TodaysFlightPanel todaysFlightPanel;

    public AdministrationFrame(){
        super("Admin Panel");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        adminPanel = new AdminPanel();
        adminPanel.setPreferredSize(new Dimension(220, 400));
        todaysFlightPanel = new TodaysFlightPanel();
        todaysFlightPanel.setPreferredSize(new Dimension(220, 400));
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(adminPanel, BorderLayout.EAST);
        add(todaysFlightPanel, BorderLayout.WEST);
    }

    private void activateFrame() {

    }
}
