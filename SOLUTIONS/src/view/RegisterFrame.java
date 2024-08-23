package view;

import controller.Kontroler;

import javax.swing.*;
import java.sql.SQLException;

public class RegisterFrame extends JFrame {

    private RegisterPanel registerPanel;

    private Kontroler kontroler;

    public RegisterFrame() {
        super("Register Frame");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        kontroler = new Kontroler();
        try {
            kontroler.connectToDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        registerPanel = new RegisterPanel();
    }

    private void layoutComps() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(registerPanel);
    }

    private void activateFrame() {
        registerPanel.setRegisterFrameListener(new RegisterFrameListener() {
            @Override
            public void loginPanelEventOccurred(String btnClicked) {
                try{
                    kontroler.registerNewUser(registerPanel.getUsername(), registerPanel.getPassword(), registerPanel.getAge());
                    System.out.println("USer added successfully :D");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
