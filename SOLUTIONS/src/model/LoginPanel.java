package model;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginPanel(){
        initComps();
        layoutComps();
        activateLoginPanel();
    }

    private void initComps() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Login");
        registerButton = new JButton("Registracija");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 0, 80);

        add(new JLabel("Korisničko ime:"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(usernameField, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 120);

        add(new JLabel("Lozinka:"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(passwordField, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);

        add(loginButton, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(60, 0, 0, 0);

        add(new JLabel("Nemate račun?"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(5, 0, 0, 0);

        add(registerButton, gbc);
    }

    private void activateLoginPanel() {

    }
}
