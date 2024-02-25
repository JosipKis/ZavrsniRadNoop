package view.AdministrationFramePckg;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    private JLabel titleLabel;
    private JTextField planeNameField;
    private JTextField destinationField;
    private JTextField flightPriceField;

    private JButton generateFlightBtn;

    public AdminPanel(){
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        titleLabel = new JLabel("New Flight");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

        planeNameField = new JTextField(15);
        destinationField = new JTextField(15);
        flightPriceField = new JTextField(15);

        generateFlightBtn = new JButton("Generate New Flight");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0,0,0,0);

        add(titleLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(20,0,0,0);

        add(new JLabel("Plane"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(2,0,0,0);

        add(planeNameField, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15,0,0,0);

        add(new JLabel("Destination"),gbc);
        gbc.gridy++;
        gbc.insets = new Insets(2,0,0,0);

        add(destinationField, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15,0,0,0);

        add(new JLabel("Flight Reservation Price"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(2,0,0,0);

        add(flightPriceField, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15,0,0,0);

        add(generateFlightBtn, gbc);
    }

    private void activatePanel() {

    }
}
