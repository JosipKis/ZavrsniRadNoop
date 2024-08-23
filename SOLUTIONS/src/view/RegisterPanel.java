package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class RegisterPanel extends JPanel implements ActionListener {

    private JTextField username;
    private JPasswordField password;
    private JTextField age;

    private JButton registerButton;

    private RegisterFrameListener registerFrameListener;

    public RegisterPanel() {
        super();
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        username = new JTextField();
        username.setPreferredSize(new Dimension(200, 30));

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(200, 30));

        age = new JTextField();
        age.setPreferredSize(new Dimension(30, 30));

        registerButton = new JButton("Register");
        registerButton.setActionCommand("register");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(new JLabel("Username:"), gbc);
        gbc.gridy++;

        add(username, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);

        add(new JLabel("Password:"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(password, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);

        add(new JLabel("Age:"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(age, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);

        add(registerButton, gbc);
    }

    private void activatePanel() {
        registerButton.addActionListener(this);
    }

    public void setRegisterFrameListener(RegisterFrameListener registerFrameListener) {
        this.registerFrameListener = registerFrameListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (registerFrameListener != null) {
            registerFrameListener.loginPanelEventOccurred(e.getActionCommand());
        }
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public int getAge() {
        return Integer.parseInt(age.getText());
    }
}
