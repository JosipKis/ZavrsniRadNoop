package view;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import controller.Kontroler;

import javax.swing.*;
import java.net.URL;
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
        URL iconURL = getClass().getResource("icons/airplane.png");
        assert iconURL != null;
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        initComps();
        layoutComps();
        activateLoginFrame();
    }

    private void initComps() {
        try {
            UIManager.setLookAndFeel( new FlatMacLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        kontroler = Kontroler.getInstance();
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
                kontroler.dissconnect();
                setVisible(false);
                dispose();
                new RegisterFrame();
            }
        });
    }
}
