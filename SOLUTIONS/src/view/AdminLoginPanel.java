package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginPanel extends JPanel implements ActionListener {

    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginBtn;

    private final String ADMINUSERNAME = "Admin";
    private final String ADMINPASSWORD = "superStrongPassword";
    private String enteredUserName;
    private String enteredPassword;

    private AdminLoginListener adminLoginListener;

    public AdminLoginPanel(){
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(10);
        loginBtn = new JButton("Login");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0,0,0,0);

        add(new JLabel("Enter Admin Username"), gbc);
        gbc.gridy++;

        add(userNameField, gbc);
        gbc.gridy++;

        gbc.insets = new Insets(20,0,0,0);
        add(new JLabel("Enter Admin Password"), gbc);
        gbc.gridy++;

        gbc.insets = new Insets(0,0,0,0);
        add(passwordField, gbc);
        gbc.gridy++;

        gbc.insets = new Insets(10,0,0,0);
        add(loginBtn, gbc);

    }

    private void activateFrame() {

        loginBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (adminLoginListener != null) {
            enteredUserName = userNameField.getText();
            System.out.println(enteredUserName); //delete, just for testing
            enteredPassword = passwordObtainer();
            System.out.println(enteredPassword); //delete, just for testing
            adminLoginListener.loginBtnPressed();
        }
    }

    public void setAdminLoginListener(AdminLoginListener adminLoginListener) {
        this.adminLoginListener = adminLoginListener;
    }

    public String getADMINUSERNAME() {
        return ADMINUSERNAME;
    }

    public String getADMINPASSWORD() {
        return ADMINPASSWORD;
    }

    public String getEnteredUserName() {
        return enteredUserName;
    }

    public String getEnteredPassword() {
        return enteredPassword;
    }

    private String passwordObtainer(){
        char[] obtainedPassword = passwordField.getPassword();
        String tempPasswd = "";
        for (int c = 0; c < obtainedPassword.length; c++){
            tempPasswd += obtainedPassword[c];
        }

        return tempPasswd;
    }
}
