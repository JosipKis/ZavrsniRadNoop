package model;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private LoginPanel loginPanel;

    public LoginFrame(){
        super("MR. KIŠ FLIGHTS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(300,400);
        setLocationRelativeTo(null);
        initComps();
        layoutComps();
        activateLoginFrame();
    }

    private void initComps() {
        loginPanel = new LoginPanel();
    }

    private void layoutComps() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(loginPanel);
    }

    private void activateLoginFrame() {

    }
}
