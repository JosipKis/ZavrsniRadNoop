package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class PurchaseCompletedPanel extends JPanel {

    private JLabel thankYouLabel;
    private JTable ticketInfoTable;
    private JButton refundTicketBtn;
    private JButton print2PDFTicketBtn;
    private JScrollPane scrollPane;

    private String[] headerNames;
    private DefaultTableModel tableModel;
    private TableColumnModel tableColumnModel;

    public PurchaseCompletedPanel(){
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        thankYouLabel = new JLabel("Thank you for you ticket purchase!");
        thankYouLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

        headerNames = new String[]{"Plane", "Destination", "Plane reservation cost", "Flight ID"};
        tableModel = new DefaultTableModel(headerNames, 0){

            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        ticketInfoTable = new JTable(tableModel);
        ticketInfoTable.getTableHeader().setReorderingAllowed(false);
        scrollPane =  new JScrollPane(ticketInfoTable);
        scrollPane.setPreferredSize(new Dimension(500, 40));
        tableColumnModel = ticketInfoTable.getColumnModel();
        tableColumnModel.getColumn(0).setResizable(false);
        tableColumnModel.getColumn(1).setResizable(false);
        tableColumnModel.getColumn(2).setResizable(false);
        tableColumnModel.getColumn(3).setResizable(false);
        tableModel.addRow(new Object[]{"hehe", "nibber", "kof", "lolXd"});

        print2PDFTicketBtn = new JButton("Save Ticket as PDF");
        refundTicketBtn = new JButton("Refund Ticket");

    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(thankYouLabel, gbc);
        gbc.gridy++;

        add(Box.createVerticalStrut(10), gbc);
        gbc.gridy++;

        add(scrollPane, gbc);
        gbc.gridy++;

        add(Box.createVerticalStrut(40), gbc);
        gbc.gridy++;

        add(print2PDFTicketBtn, gbc);
        gbc.gridy++;

        add(Box.createVerticalStrut(15), gbc);
        gbc.gridy++;

        add(refundTicketBtn, gbc);
    }

    private void activateComps() {

    }
}
