package view;

import controller.Kontroler;
import model.Ticket;
import model.TicketOnProfileRenderer;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class UserProfilePanel extends JPanel {

    private JList<String> list;
    private JScrollPane scrollPane;

    private List<Ticket> listOfTickets;

    private Kontroler kontroler;

    public UserProfilePanel() {
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        kontroler = new Kontroler();
        kontroler.connectToDatabase();

        listOfTickets = kontroler.getAllTicketsByID(Kontroler.getCurrentUserID());

        String[] listOfTicketsString = new String[listOfTickets.size()];

        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (int i = 0; i < listOfTickets.size(); i++) {
            listOfTicketsString[i] = listOfTickets.get(i).getTakeOffCity();
            listOfTicketsString[i] += listOfTickets.get(i).getDestinationCity();
            listOfTicketsString[i] += listOfTickets.get(i).getStartDate();
            listOfTicketsString[i] += listOfTickets.get(i).getStartDate();
            listOfTicketsString[i] += listOfTickets.get(i).getPlane();
            listOfTicketsString[i] += listOfTickets.get(i).getTotalPrice();
            listModel.addElement(listOfTicketsString[i]);
        }

        System.out.println(Arrays.toString(listOfTicketsString));

        list = new JList<>(listModel);
        list.setCellRenderer(new TicketOnProfileRenderer());

        scrollPane = new JScrollPane(list);
        scrollPane.createVerticalScrollBar();
    }

    private void layoutComps() {
        add(scrollPane);
    }

    private void activateComps() {

    }
}