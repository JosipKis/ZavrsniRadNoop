package view.AdministrationFramePckg;

import javax.swing.*;
import java.awt.*;

public class TodaysFlightPanel extends JPanel {

    private JMenu menu;
    private JScrollPane scrollPane;

    public TodaysFlightPanel(){
        initComps();
        layoutComps();
    }

    private void initComps() {
        menu = new JMenu();
        menu.setPreferredSize(new Dimension(200,300));
        scrollPane = new JScrollPane(menu);
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;

        add(new JLabel("Today's flights: "), gbc);
        gbc.gridy++;

        add(scrollPane, gbc);
    }
}
