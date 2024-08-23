package view;

import controller.Kontroler;

import javax.swing.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private LoginPanel loginPanel;

    private Kontroler kontroler;

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
        kontroler = new Kontroler();
        try {
            kontroler.connectToDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        loginPanel = new LoginPanel();
    }

    private void layoutComps() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(loginPanel);
        revalidate();
        repaint();
    }

    private void activateLoginFrame() {
        loginPanel.setLoginFrameListener(new LoginFrameListener() {
            @Override
            public void loginPerformed(String username, String password) {
                try{
                    System.out.println("Username: " + username + "\nPassword: " + password);
                    kontroler.autehnticateLogin(username, password);

                    try {
                        if (kontroler.getUserRole().equals("admin")) {
                            setVisible(false);
                            dispose();
                            new AdminFrame();
                        } else if (kontroler.getUserRole().equals("user")) {
                            setVisible(false);
                            dispose();
                            new UserFrame();
                        }
                    }catch (NullPointerException npe){
                        JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password!", "Login error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void registerPerformed() {
                System.out.println("Register button clicked");
            }
        });
    }
}
