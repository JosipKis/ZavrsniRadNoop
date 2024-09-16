package view;

import controller.Kontroler;
import model.Ticket;

import javax.swing.*;
import java.util.List;

public class UserProfilePanel extends JPanel {

    private JList<String> list;

    private List<Ticket> listOfTickets;

    private Kontroler kontroler;

    public UserProfilePanel() {
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        kontroler = new Kontroler();

        listOfTickets = kontroler.getAllTicketsByID(Kontroler.getCurrentUserID());

        String[] listOfTicketsString = new String[listOfTickets.size()];

        for (int i = 0; i < listOfTickets.size(); i++) {
            listOfTicketsString[i] = listOfTickets.get(i).toString();
        }

        list = new JList<>(listOfTicketsString);
    }

    private void layoutComps() {
        add(list);
    }

    private void activateComps() {

    }
}