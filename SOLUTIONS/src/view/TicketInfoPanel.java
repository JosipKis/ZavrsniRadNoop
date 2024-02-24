package view;

import model.FlightsINT;
import model.ObserverINT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class TicketInfoPanel extends JPanel implements ObserverINT {

    private JTable flightInfoTable;
    private String[] headerNames;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private TableColumnModel tableColumnModel;

    public TicketInfoPanel(){
        initComps();
        layoutComps();
    }

    private void initComps() {
        headerNames = new String[]{"Plane", "Destination", "Plane reservation cost", "Flight ID"};
        tableModel = new DefaultTableModel(headerNames, 0) {

           @Override
           public boolean isCellEditable(int row, int column){
               return false;
           }
        };

        flightInfoTable = new JTable(tableModel);
        flightInfoTable.getTableHeader().setReorderingAllowed(false);
        tableColumnModel = flightInfoTable.getColumnModel();
        tableColumnModel.getColumn(0).setResizable(false);
        tableColumnModel.getColumn(1).setResizable(false);
        tableColumnModel.getColumn(2).setResizable(false);
        tableColumnModel.getColumn(3).setResizable(false);
        scrollPane = new JScrollPane(flightInfoTable);

    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(FlightsINT flight) {
        if (tableModel.getRowCount() == 1){
            tableModel.removeRow(0);
        }
        tableModel.addRow(new Object[]{flight.getPlane(), flight.getDestination(), flight.getPrice(), flight.getID()});
    }
}
