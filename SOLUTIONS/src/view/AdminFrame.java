package view;

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
        kontroler.connectToDatabase();

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
        Kontroler.determineUsersTheme(kontroler.getUsersTheme(Kontroler.getCurrentUserID()), lightThemeRB, darkThemeRB);

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

        revalidate();
        repaint();
    }

    private void activateFrame() {
        adminPanel.setPlaneCreationListener(new PlaneCreationListener() {
            @Override
            public void createBtnPressed() {
                System.out.println(adminPanel.getNewPlaneSpecs());
                if (adminPanel.getNewPlaneSpecs().get(0).isEmpty() || adminPanel.getNewPlaneSpecs().get(1).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ime zrakoplova i proizvođaća moraju biti ispunjeni!", "Nepotpune informacije", JOptionPane.ERROR_MESSAGE);
                } else if (adminPanel.getNewPlaneSpecs().get(2).equals("0") && adminPanel.getNewPlaneSpecs().get(3).equals("0") && adminPanel.getNewPlaneSpecs().get(4).equals("0")) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati i unijeti cijenu barem jedom razredu (Ako je jedna unesena mora biti >0)!", "Nepotpune informacije!", JOptionPane.ERROR_MESSAGE);
                } else {
                    kontroler.addPlaneToDB(adminPanel.getNewPlaneSpecs());
                    adminPanel.resetCreatePanelForm();
                    JOptionPane.showMessageDialog(null, "Novi avion uspješno dodan!", "Uspješno dodavanje", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void checkBoxSelected(String actionCommand) {
                switch (actionCommand) {
                    case "fst":
                        System.out.println("First class selected");
                        break;
                    case "bsn":
                        System.out.println("Business class selected");
                        break;
                    case "eco":
                        System.out.println("Economy class selected");
                        break;
                }
            }
        });
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
