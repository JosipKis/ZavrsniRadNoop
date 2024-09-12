package view;

import javax.swing.*;
import java.net.URL;

public class AdminFrame extends JFrame {

    private AdminPanel adminPanel;

    public AdminFrame() {
        super("Admin Frame");
        setSize(630, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        URL iconURL = getClass().getResource("icons/airplane.png");
        assert iconURL != null;
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        adminPanel = new AdminPanel();
    }

    private void layoutComps() {
        add(adminPanel);
    }

    private void activateFrame() {

    }
}
