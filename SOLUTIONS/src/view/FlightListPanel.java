package view;

import controller.Kontroler;
import model.DataBase;
import model.Flight;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FlightListPanel extends JPanel {

    private JTable table;
    private AbstractTableModel abstractTableModel;
    private String[] columnHeaders;
    private JScrollPane scrollPane;

    private Kontroler kontroler;
    private DataBase dataBase;

    private List<Flight> flights;

    public FlightListPanel(){
        super();
        initComps();
        layoutComps();
    }

    private void initComps() {
        kontroler = new Kontroler();
        dataBase = new DataBase();

        try {
            kontroler.connectToDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        flights = kontroler.getAllFlights();

        table = new JTable();
        columnHeaders = new String[]{"Plane", "Departure", "Destination", "Departure time"};
        this.abstractTableModel = initTable();
        table.setModel(abstractTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void layoutComps() {
        add(new JScrollPane(table));
    }

    private AbstractTableModel initTable() {
        AbstractTableModel tableModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return flights.size();
            }

            @Override
            public int getColumnCount() {
                return columnHeaders.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Flight flight = flights.get(rowIndex);
                switch(columnIndex){
                    case 0:
                        return flight.getFlightNumber();
                    case 1:
                        return flight.getDeparture();
                    case 2:
                        return flight.getDestination();
                    case 3:
                        return flight.getDepartureTime();
                    case 4:
                        return flight.getPlane();
                    default:
                        throw new IllegalArgumentException("The column index is not possible for this case.");
                }
            }

            @Override
            public String getColumnName(int column) {
                return columnHeaders[column];
            }
        };

        return tableModel;
    }

    public void activateTable(JTable someTable, UserPanel userPanel) {
        someTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    StringBuilder flightSelection = new StringBuilder();

                    for (int column = 0; column < target.getColumnCount(); column++) {
                        Object value = target.getValueAt(row, column);
                        flightSelection.append(value).append(", ");
                    }

                    if (userPanel != null) {
                        userPanel.setText(flightSelection.toString());
                    }
                }
            }
        });
    }

    public JTable getDaTable(){
        return table;
    }
}
