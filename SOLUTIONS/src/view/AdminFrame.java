package view;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import controller.Kontroler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class AdminFrame extends JFrame implements ActionListener {

    private AdminPanel adminPanel;

    private JMenuBar menuBar;
    private JMenu jMenu;
    private JMenuItem odjavaBtn;
    private JMenuItem mojProfil;
    private JMenuItem kupovinaKarata;
    private JRadioButtonMenuItem lightThemeRB;
    private JRadioButtonMenuItem darkThemeRB;
    private ButtonGroup buttonGroup;

    private Kontroler kontroler;

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
        kontroler = new Kontroler();

        adminPanel = new AdminPanel();

        menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jMenu = new JMenu("IZBORNIK");

        odjavaBtn = new JMenuItem("ODJAVA");
        odjavaBtn.setActionCommand("odjava");
        odjavaBtn.addActionListener(this);

        mojProfil = new JMenuItem("MOJ PROFIL");
        mojProfil.setActionCommand("mojProfil");
        mojProfil.addActionListener(this);

        kupovinaKarata = new JMenuItem("KUPOVINA KARATA");
        kupovinaKarata.setActionCommand("kupovinaKarata");
        kupovinaKarata.addActionListener(this);

        lightThemeRB = new JRadioButtonMenuItem("Svijetla tema");
        lightThemeRB.setActionCommand("lightTheme");
        lightThemeRB.addActionListener(this);

        darkThemeRB = new JRadioButtonMenuItem("Tamna tema");
        darkThemeRB.setActionCommand("darkTheme");
        darkThemeRB.addActionListener(this);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(lightThemeRB);
        buttonGroup.add(darkThemeRB);
        determineUsersTheme();

        menuBar.add(jMenu);
        jMenu.add(mojProfil);
        jMenu.add(kupovinaKarata);
        jMenu.addSeparator();
        jMenu.add(lightThemeRB);
        jMenu.add(darkThemeRB);
        jMenu.addSeparator();
        jMenu.add(odjavaBtn);

        setJMenuBar(menuBar);
    }

    private void layoutComps() {
        add(adminPanel);
    }

    private void activateFrame() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "odjava":
                dispose();
                new LoginFrame();
                break;
            case "mojProfil":
                dispose();
                new UserProfileFrame();
                break;
            case "kupovinaKarata":
                dispose();
                new UserFrame();
                break;
            case "lightTheme":
                kontroler.setUsersThemeInDB("light", Kontroler.getCurrentUserID());
                determineUsersTheme();
                break;
            case "darkTheme":
                kontroler.setUsersThemeInDB("dark", Kontroler.getCurrentUserID());
                determineUsersTheme();
                break;
        }
    }

    // Ponavljajući kod, rectify this!!!
    private void determineUsersTheme() {
        String userTheme = kontroler.getUsersTheme(Kontroler.getCurrentUserID());

        if (userTheme.equals("light")) {
            lightThemeRB.setSelected(true);
            try {
                UIManager.setLookAndFeel(new FlatMacLightLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }
        } else if (userTheme.equals("dark")) {
            darkThemeRB.setSelected(true);
            try {
                UIManager.setLookAndFeel(new FlatMacDarkLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }
        }

        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
        repaint();
    }
}
