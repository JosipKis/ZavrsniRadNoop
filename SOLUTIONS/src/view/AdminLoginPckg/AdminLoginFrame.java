package view.AdminLoginPckg;

import view.AdministrationFramePckg.AdministrationFrame;

import javax.swing.*;
import java.awt.*;

public class AdminLoginFrame extends JFrame  {

    private AdminLoginPanel adminLoginPanel;

    public AdminLoginFrame(){
        super("Admin Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        adminLoginPanel = new AdminLoginPanel();
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(adminLoginPanel, BorderLayout.CENTER);
    }

    private void activateFrame() {
        adminLoginPanel.setAdminLoginListener(new AdminLoginListener() {
            @Override
            public void loginBtnPressed() {
                if ((adminLoginPanel.getEnteredUserName().equals(adminLoginPanel.getADMINUSERNAME())) && (adminLoginPanel.getEnteredPassword().equals(adminLoginPanel.getADMINPASSWORD()))){
                    System.out.println("Login successful!");
                    dispose();
                    new AdministrationFrame();
                }else {
                    System.out.println("Unsuccessful login!");
                    JOptionPane.showMessageDialog(null, "Inaccurate password or username!", "Failed Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
