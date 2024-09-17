package view;

import controller.Kontroler;
import controller.command.*;

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
    private JMenuItem mojProfil;
    private JRadioButtonMenuItem lightThemeRB;
    private JRadioButtonMenuItem darkThemeRB;
    private ButtonGroup buttonGroup;

    private Kontroler kontroler;
    private CommandInvoker commandInvoker;

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
        kontroler = Kontroler.getInstance();
        kontroler.connectToDatabase();

        commandInvoker = new CommandInvoker();

        flightListPanel = new FlightListPanel();
        flightListPanel.setPreferredSize(new Dimension(300, 250));

        userPanel = new UserPanel(flightListPanel);
        userPanel.setPreferredSize(new Dimension(690, 208));
        userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        flightListPanel.activateTable(flightListPanel.getDaTable(), userPanel);

        menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jMenu = new JMenu("IZBORNIK");

        odjavaBtn = new JMenuItem("ODJAVA");
        odjavaBtn.setActionCommand("odjava");
        odjavaBtn.addActionListener(this);

        mojProfil = new JMenuItem("MOJ PROFIL");
        mojProfil.setActionCommand("mojProfil");
        mojProfil.addActionListener(this);

        lightThemeRB = new JRadioButtonMenuItem("Svijetla tema");
        lightThemeRB.setActionCommand("lightTheme");
        lightThemeRB.addActionListener(this);

        darkThemeRB = new JRadioButtonMenuItem("Tamna tema");
        darkThemeRB.setActionCommand("darkTheme");
        darkThemeRB.addActionListener(this);

        JMenuItem undo = new JMenuItem("Undo");
        undo.setActionCommand("undo");
        undo.addActionListener(this);
        undo.setAccelerator(KeyStroke.getKeyStroke("ctrl shift Z"));

        JMenuItem redo = new JMenuItem("Redo");
        redo.setActionCommand("redo");
        redo.addActionListener(this);
        redo.setAccelerator(KeyStroke.getKeyStroke("ctrl shift Y"));

        buttonGroup = new ButtonGroup();
        buttonGroup.add(lightThemeRB);
        buttonGroup.add(darkThemeRB);
        Kontroler.determineUsersTheme(kontroler.getUsersTheme(Kontroler.getCurrentUserID()), lightThemeRB, darkThemeRB);

        menuBar.add(jMenu);
        jMenu.add(mojProfil);
        jMenu.addSeparator();
        jMenu.add(lightThemeRB);
        jMenu.add(darkThemeRB);
        jMenu.addSeparator();
        jMenu.add(undo);
        jMenu.add(redo);
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
        userPanel.setBookingListener(new SingleButtonOnPanelListener() {
            @Override
            public void bookButtonClicked(String button) {
                List<String> lista = flightListPanel.getDetailsOfFlight();
                System.out.println(lista);
                if (lista != null  && userPanel.isAClassSelected()){
                    lista.add(userPanel.getTotalPriceText());
                    kontroler.addTicketToDb(lista);
                    dispose();
                    new Receipt4FlightFrame(lista);
                }else {
                    JOptionPane.showMessageDialog(null, "Molimo odaberite let i željenu klasu prije rezervacije karte!", "Greška: let nije odabran!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        userPanel.setComboBoxListener(new ComboBoxListener() {
            @Override
            public void comboBoxPressed(String selection) {
                switch (selection){
                    case "Najstarije gore" :
                        System.out.println("Sortirano s najstarijim na vrhu");
                        Command sortByEarliestCommand = new SortByEarliestCommand(flightListPanel);
                        commandInvoker.runCommand(sortByEarliestCommand);
                        break;
                    case "Najnovije gore" :
                        System.out.println("Sortirano s najnovijim na vrhu");
                        Command sortByLatestCommand = new SortByLatestCommand(flightListPanel);
                        commandInvoker.runCommand(sortByLatestCommand);
                        break;
                    case "Prva klasa" :
                        System.out.println("Odabran prikaz letova s prvom klasom");
                        Command isFirstClass = new IsFirstClassCommand(flightListPanel);
                        commandInvoker.runCommand(isFirstClass);
                        break;
                    case "Poslovna klasa" :
                        System.out.println("Poslovna klasa");
                        Command isBussinessClass = new IsBussinesClassCommand(flightListPanel);
                        commandInvoker.runCommand(isBussinessClass);
                        break;
                    case "Ekonomična klasa" :
                        System.out.println("Ekonomicna klasa");
                        Command isEconomyClass = new IsEconomyClassCommand(flightListPanel);
                        commandInvoker.runCommand(isEconomyClass);
                        break;
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
                Kontroler.determineUsersTheme(kontroler.getUsersTheme(Kontroler.getCurrentUserID()), lightThemeRB, darkThemeRB);
                SwingUtilities.updateComponentTreeUI(this);
                revalidate();
                repaint();
                break;

            case "darkTheme":
                System.out.println("Dark theme selected");
                kontroler.setUsersThemeInDB("dark", Kontroler.getCurrentUserID());
                Kontroler.determineUsersTheme(kontroler.getUsersTheme(Kontroler.getCurrentUserID()), lightThemeRB, darkThemeRB);
                SwingUtilities.updateComponentTreeUI(this);
                revalidate();
                repaint();
                break;

            case "mojProfil":
                dispose();
                new UserProfileFrame();
                break;

            case "undo":
                System.out.println("undo");
                commandInvoker.undoCommand();
                break;

            case "redo":
                System.out.println("redo");
                commandInvoker.redoCommand();
                break;
        }
    }

}