package view;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame {

    private UserPanel userPanel;

    public UserFrame() {
        super("User Frame");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        userPanel = new UserPanel();
        userPanel.setPreferredSize(new Dimension(700, 220));
        userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void layoutComps() {
        setLayout(new BorderLayout());

        add(userPanel, BorderLayout.SOUTH);
    }

    private void activateFrame() {

    }
}
