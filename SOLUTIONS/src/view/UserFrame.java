package view;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import controller.Kontroler;
import view.icons.UserPanelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

public class UserFrame extends JFrame implements ActionListener {

    private UserPanel userPanel;
    private FlightListPanel flightListPanel;

    private JMenuBar menuBar;
    private JMenu jMenu;
    private JMenuItem odjavaBtn;
    private JRadioButtonMenuItem lightThemeRB;
    private JRadioButtonMenuItem darkThemeRB;
    private ButtonGroup buttonGroup;

    private UserPanelListener userPanelListener;

    private Kontroler kontroler;

    public UserFrame() {
        super("Mr. KIŠ FLIGHTS");
        setSize(700, 500);
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
        activateUserFrame();
    }

    private void initComps() {
        kontroler = new Kontroler();
        kontroler.connectToDatabase();

        userPanel = new UserPanel();
        userPanel.setPreferredSize(new Dimension(690, 210));
        userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        flightListPanel = new FlightListPanel();
        flightListPanel.setPreferredSize(new Dimension(300, 250));

        flightListPanel.activateTable(flightListPanel.getDaTable(), userPanel);

        menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jMenu = new JMenu("IZBORNIK");

        odjavaBtn = new JMenuItem("ODJAVA");
        odjavaBtn.setActionCommand("odjava");
        odjavaBtn.addActionListener(this);

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
        jMenu.add(lightThemeRB);
        jMenu.add(darkThemeRB);
        jMenu.addSeparator();
        jMenu.add(odjavaBtn);

        setJMenuBar(menuBar);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());

        add(userPanel, BorderLayout.SOUTH);

        add(flightListPanel, BorderLayout.NORTH);

        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
        repaint();
    }

    private void activateUserFrame() {
        userPanel.setBookingListener(new BookingListener() {
            @Override
            public void bookButtonClicked(String button) {
                List<String> lista = flightListPanel.getDetailsOfFlight();
                if (lista != null && userPanel.isAClassSelected()){
                    lista.add(userPanel.getTotalPriceText());
                    kontroler.addTicketToDb(lista);
                    dispose();
                    new Receipt4FlightFrame(lista);
                }else {
                    JOptionPane.showMessageDialog(null, "Molimo odaberite let i željenu klasu prije rezervacije karte!", "Greška: let nije odabran!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "odjava":
                dispose();
                new LoginFrame();
                break;

            case "lightTheme":
                System.out.println("Light theme selected");
                kontroler.setUsersThemeInDB("light", Kontroler.getCurrentUserID());
                determineUsersTheme();
                break;

            case "darkTheme":
                System.out.println("Dark theme selected");
                kontroler.setUsersThemeInDB("dark", Kontroler.getCurrentUserID());
                determineUsersTheme();
                break;
        }
    }

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