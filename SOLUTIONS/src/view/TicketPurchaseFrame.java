package view;

import model.FlightsINT;
import model.ObserverINT;

import javax.swing.*;
import java.awt.*;

public class TicketPurchaseFrame extends JFrame {

    private TicketInfoPanel ticketInfoPanel;
    private TicketOptionsPanel ticketOptionsPanel;

    public TicketPurchaseFrame(){
        super("Mr.Kiš Flights");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        initComps();
        layoutComps();
        activateFrame();
    }

    private void initComps() {
        ticketInfoPanel = new TicketInfoPanel();
        ticketInfoPanel.setPreferredSize(new Dimension(getWidth(), 200));
        ticketOptionsPanel = new TicketOptionsPanel();
        ticketOptionsPanel.setPreferredSize(new Dimension(getWidth(), 200));
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(ticketInfoPanel, BorderLayout.CENTER);
        add(ticketOptionsPanel, BorderLayout.SOUTH);

    }

    private void activateFrame() {
        ticketOptionsPanel.setTicketOptionsListener(new TicketOptionsListener() {
            @Override
            public void btnClicked(String btn) {
                if (btn.equals("calculate")){
                    System.out.println("The price is being calculated...");
                    ticketOptionsPanel.calculatePrice();
                } else if (btn.equals("book")) {
                    System.out.println("Thank you for your purchase!");
                    ticketOptionsPanel.calculatePrice();
                    dispose();
                    new PurchaseCompletedFrame();
                }
            }
        });

        ticketOptionsPanel.setFlightCBListener(new FlightCBListener() {
            @Override
            public void checkBoxOptionSelected(FlightsINT selection) {
                System.out.println("Selection is "+ selection);
                ticketInfoPanel.update(selection);
            }
        });
    }
}
