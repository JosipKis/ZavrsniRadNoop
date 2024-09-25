package view;

import com.toedter.calendar.JDateChooser;
import controller.Kontroler;
import model.Ticket;
import model.TicketOnProfileRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class UserProfilePanel extends JPanel implements ActionListener {

    private JList<String> list;
    private JScrollPane scrollPane;
    private JTextField textField;
    private JButton printSelectedButton;

    private JDateChooser beginDate;
    private JTextField beginDateField;

    private SimpleDateFormat sdf;
    private JDateChooser endDate;
    private JTextField endDateField;

    private List<Ticket> listOfTickets;
    private List<Ticket> dates;
    private DefaultListModel<String> sortedDates;
    private Ticket selectedTicketDetails;

    private SingleButtonOnPanelListener buttonOnPanelListener;
    private Kontroler kontroler;

    public UserProfilePanel() {
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        kontroler = Kontroler.getInstance();
        kontroler.connectToDatabase();

        textField = new JTextField();
        textField.setColumns(7);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFocusable(false);

        dates = new ArrayList<>();
        sortedDates = new DefaultListModel<>();

        listOfTickets = kontroler.getAllTicketsByID(Kontroler.getCurrentUserID());

        String[] listOfTicketsString = new String[listOfTickets.size()];

        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (int i = 0; i < listOfTickets.size(); i++) {
            listOfTicketsString[i] = "Karta br. " + (i + 1) + ",  ";
            listOfTicketsString[i] += listOfTickets.get(i).getTakeOffCity() + " --> ";
            listOfTicketsString[i] += listOfTickets.get(i).getDestinationCity() + " | ";
            listOfTicketsString[i] += listOfTickets.get(i).getStartDate() + " - ";
            listOfTicketsString[i] += listOfTickets.get(i).getStartTime() + " | ";
            listOfTicketsString[i] += listOfTickets.get(i).getPlane() + ",  ";
            listOfTicketsString[i] += listOfTickets.get(i).getTotalPrice() + " ";
            listModel.addElement(listOfTicketsString[i]);
            dates.add(listOfTickets.get(i));
        }

        System.out.println(Arrays.toString(listOfTicketsString));

        list = new JList<>(listModel);
        list.setCellRenderer(new TicketOnProfileRenderer());

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final String value = list.getSelectedValue();
                    selectedTicketDetails = listOfTickets.get(list.getSelectedIndex());
                    System.out.println(selectedTicketDetails);
                    String[] valueAsArray = value.split(",");
                    textField.setText(valueAsArray[0]);
                }
            }
        });

        sdf = new SimpleDateFormat("yyyy-MM-dd");

        beginDateField = new JTextField(10);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 0);
        beginDate = new JDateChooser(c.getTime());
        beginDate.setDateFormatString("yyyy-MM-dd");
        beginDate.setPreferredSize(new Dimension(beginDateField.getPreferredSize().width, beginDateField.getPreferredSize().height));

        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.YEAR, 0);
        endDate = new JDateChooser(c1.getTime());
        endDate.setDateFormatString("yyyy-MM-dd");
        endDate.setPreferredSize(new Dimension(beginDateField.getPreferredSize().width, beginDateField.getPreferredSize().height));

        scrollPane = new JScrollPane(list);
        scrollPane.createVerticalScrollBar();

        printSelectedButton = new JButton("Print");
        printSelectedButton.setActionCommand("print");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,20,0);

        add(scrollPane, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,0,0);

        add(new JLabel("Unesite datum pocetka: "), gbc);
        gbc.gridy++;

        add(beginDate, gbc);
        gbc.gridy++;

        add(new JLabel("Unesite datum kraja: "), gbc);
        gbc.gridy++;

        add(endDate, gbc);
        gbc.gridy++;

        add(new JLabel("Odabrana karta: "), gbc);
        gbc.gridy++;

        add(textField, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(10,0,0,0);

        add(printSelectedButton, gbc);
    }

    private void activateComps() {
        printSelectedButton.addActionListener(this);
    }

    public void setSingleUseListener(SingleButtonOnPanelListener singleUseListener){
        this.buttonOnPanelListener = singleUseListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonOnPanelListener != null) {
            textField.setText("");
            buttonOnPanelListener.bookButtonClicked(e.getActionCommand());
        }
    }

    public Ticket getSelectedTicketDetails(){
        return selectedTicketDetails;
    }

    public void resetFunctionallity(){
        selectedTicketDetails = null;
    }

    public String getBeginDate(){
        return sdf.format(beginDate.getDate());
    }

    public String getEndDate(){
        return sdf.format(endDate.getDate());
    }

    public void compareDates(){
        for (Ticket ticket : dates) {
            String date = ticket.getStartDate();
            System.out.println(date);
            if (date.compareTo(getBeginDate()) >= 0 && date.compareTo(getEndDate()) <= 0) {
                sortedDates.add(0, ticket.getStartDate() + " - " + ticket.getStartTime() + " | " + ticket.getPlane() + ",  " + ticket.getTotalPrice() + " ");
                System.out.println("Datum je u opsegu");
            }
        }
        list.setModel(sortedDates);
        list.setCellRenderer(new TicketOnProfileRenderer());
    }
}