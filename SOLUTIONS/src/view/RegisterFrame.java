package view;

import controller.Kontroler;
import controller.strategy.ButtonInspector;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RegisterFrame extends JFrame {

    private RegisterPanel registerPanel;

    private ButtonInspector buttonInspector;

    private Kontroler kontroler;

    public RegisterFrame() {
        super("Register Frame");
        setSize(300, 400);
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
        kontroler = Kontroler.getInstance();
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
            public void registerButtonClicked(String btnClicked) {
                List<String> data = new ArrayList<>();
                if (Integer.parseInt(registerPanel.getAge()) > 17){
                    data.add(registerPanel.getUsername());
                    data.add(registerPanel.getPassword());
                    data.add(String.valueOf(registerPanel.getAge()));

                    buttonInspector = new ButtonInspector(btnClicked);
                    buttonInspector.pressedButton(data);
                    setVisible(false);
                    dispose();
                    new LoginFrame();
                }else {
                    JOptionPane.showMessageDialog(null, "Korisnik mora imati 18+ godina!", "Dobna granica", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
