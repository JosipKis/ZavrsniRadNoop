package view;

import controller.Kontroler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileFrame extends JFrame implements ActionListener {

    private UserProfilePanel userProfilePanel;

    private JMenuBar menuBar;
    private JMenu jMenu;
    private JMenuItem odjavaBtn;
    private JMenuItem kupovinaKarata;
    private JRadioButtonMenuItem lightThemeRB;
    private JRadioButtonMenuItem darkThemeRB;
    private ButtonGroup buttonGroup;

    private Kontroler kontroler;

    public UserProfileFrame() {
        super("Moj profil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        kontroler = new Kontroler();
        kontroler.connectToDatabase();

        userProfilePanel = new UserProfilePanel();

        menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jMenu = new JMenu("IZBORNIK");

        odjavaBtn = new JMenuItem("ODJAVA");
        odjavaBtn.setActionCommand("odjava");
        odjavaBtn.addActionListener(this);

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
        Kontroler.determineUsersTheme(kontroler.getUsersTheme(Kontroler.getCurrentUserID()), lightThemeRB, darkThemeRB);

        menuBar.add(jMenu);
        jMenu.add(kupovinaKarata);
        jMenu.addSeparator();
        jMenu.add(lightThemeRB);
        jMenu.add(darkThemeRB);
        jMenu.addSeparator();
        jMenu.add(odjavaBtn);

        setJMenuBar(menuBar);
    }

    private void layoutComps() {
        add(userProfilePanel);
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
            case "kupovinaKarata":
                dispose();
                new UserFrame();
                break;
            case "lightTheme":
                System.out.println("Light theme");
                kontroler.setUsersThemeInDB("light", Kontroler.getCurrentUserID());
                Kontroler.determineUsersTheme(kontroler.getUsersTheme(Kontroler.getCurrentUserID()), lightThemeRB, darkThemeRB);
                SwingUtilities.updateComponentTreeUI(this);
                revalidate();
                repaint();
                break;
            case "darkTheme":
                System.out.println("Dark theme");
                kontroler.setUsersThemeInDB("dark", Kontroler.getCurrentUserID());
                Kontroler.determineUsersTheme(kontroler.getUsersTheme(Kontroler.getCurrentUserID()), lightThemeRB, darkThemeRB);
                SwingUtilities.updateComponentTreeUI(this);
                revalidate();
                repaint();
                break;
        }
    }
}