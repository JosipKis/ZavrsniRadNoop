package view.EntryPanelPckg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryPanel extends JPanel implements ActionListener {

    private JButton customerBtn;
    private JButton adminBtn;
    private JLabel welcomeLabel;

    private EntryPanelListener entryPanelListener;

    public EntryPanel(){
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        customerBtn = new JButton("Purchase a ticket");
        adminBtn = new JButton("Admin login");
        welcomeLabel = new JLabel("Welcome to Mr.Kiš Flights, enjoy your flight!");
        welcomeLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));

        customerBtn.setActionCommand("customer");

        adminBtn.setActionCommand("admin");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;

        add(welcomeLabel, gbc);
        gbc.gridy++;

        add(Box.createVerticalStrut(20), gbc);
        gbc.gridy++;

        add(customerBtn, gbc);
        gbc.gridy++;

        add(Box.createVerticalStrut(20), gbc);
        gbc.gridy++;

        add(adminBtn, gbc);
    }

    private void activatePanel() {
        customerBtn.addActionListener(this);
        adminBtn.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (entryPanelListener != null){
            entryPanelListener.buttonPressed(ae.getActionCommand());
        }
    }

    public void setEntryPanelListener(EntryPanelListener entryPanelListener) {
        this.entryPanelListener = entryPanelListener;
    }
}
