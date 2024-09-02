package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserFrame extends JFrame {

    private UserPanel userPanel;
    private FlightListPanel flightListPanel;

    public UserFrame() {
        super("Mr. KIŠ FLIGHTS");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        initComps();
        layoutComps();
        activateUserFrame();
    }

    private void initComps() {
        userPanel = new UserPanel();
        userPanel.setPreferredSize(new Dimension(690, 210));
        userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        flightListPanel = new FlightListPanel();
        flightListPanel.setPreferredSize(new Dimension(300, 250));

        flightListPanel.activateTable(flightListPanel.getDaTable(), userPanel);
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
                if (lista != null){
                    dispose();
                    new Receipt4FlightFrame(lista);
                }else {
                    JOptionPane.showMessageDialog(null, "Molimo odaberite let prije rezervacije karte!", "Greška: let nije odabran!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
