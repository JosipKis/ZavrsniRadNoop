package view;

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

        menuBar.add(jMenu);
        jMenu.add(odjavaBtn);

        setJMenuBar(menuBar);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());

        add(userPanel, BorderLayout.SOUTH);

        add(flightListPanel, BorderLayout.NORTH);
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
        if (e.getActionCommand().equals("odjava")) {
            dispose();
            new LoginFrame();
        }
    }
}
