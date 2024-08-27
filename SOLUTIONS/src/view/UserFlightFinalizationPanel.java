package view;

import javax.swing.*;
import java.awt.*;

public class UserFlightFinalizationPanel extends JPanel {

    private JTextArea flightDetails;

    private JButton bookButton;

    public UserFlightFinalizationPanel(){
        super();
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        flightDetails = new JTextArea();
        flightDetails.setPreferredSize(new Dimension(200, 80));
        flightDetails.setEditable(false);
        flightDetails.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        bookButton = new JButton("Book");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(flightDetails, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);

        add(bookButton, gbc);
    }

    private void activatePanel() {

    }

    public void setText(String text){
        flightDetails.setText(text);
    }
}
