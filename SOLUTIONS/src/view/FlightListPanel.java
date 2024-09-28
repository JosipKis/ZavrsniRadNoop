package view;

import controller.Kontroler;
import model.DataBase;
import model.Flight;
import model.observer.AuxCLS;
import model.observer.ObservablePanelCLS;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class FlightListPanel extends JPanel {

    private JTable table;
    private AbstractTableModel abstractTableModel;
    private String[] columnHeaders;
    private JScrollPane scrollPane;

    private Kontroler kontroler;
    private DataBase dataBase;
    private ObservablePanelCLS observablePanelCLS;
    private AuxCLS auxCLS;

    private String planeID;
    private List<Integer> planeClasses;
    private List<Flight> flights;
    private List<Flight> backupFlights;
    private StringBuilder flightSelection;
    private List<String> flightDetails;

    public FlightListPanel(){
        super();
        initComps();
        layoutComps();
    }

    private void initComps() {
        kontroler = Kontroler.getInstance();
        dataBase = new DataBase();
        observablePanelCLS = ObservablePanelCLS.getInstance();
        auxCLS = AuxCLS.getInstance();

        try {
            kontroler.connectToDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        flights = kontroler.getAllFlights();
        backupFlights = new ArrayList<>(flights);
        flightDetails = new ArrayList<>();

        table = new JTable();
        columnHeaders = new String[]{"Plane", "Departure", "Destination", "Departure date", "Departure time"};
        this.abstractTableModel = initTable();
        table.setModel(abstractTableModel);
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);

        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(600,249));
    }

    private void layoutComps() {
        add(scrollPane);
    }

    private AbstractTableModel initTable() {

          abstractTableModel = new AbstractTableModel() {
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
                        planeID = kontroler.getPlaneNameByID(flight.getPlane());
                        return kontroler.getPlaneNameByID(flight.getPlane());
                    case 1:
                        return flight.getDeparture();
                    case 2:
                        return flight.getDestination();
                    case 3:
                        return flight.getDepartureDate();
                    case 4:
                        return flight.getDepartureTime();
                    case 5:
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

        return abstractTableModel;
    }

    public void activateTable(JTable someTable, UserPanel userPanel) {
        someTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    Flight selectedFlight = flights.get(row);

                    if (selectedFlight.isActive()) {
                        StringBuilder flightSelection = new StringBuilder();

                        for (int column = 0; column < target.getColumnCount(); column++) {
                            Object value = target.getValueAt(row, column);
                            flightSelection.append(value).append(", ");
                            flightDetails.add(value.toString());
                        }

                        if (userPanel != null) {
                            planeClasses = kontroler.getPlaneClasses(target.getValueAt(row, 0).toString());
                            System.out.println(target.getValueAt(row, 0));
                            userPanel.disableOptions();
                            userPanel.enableSelection(planeClasses.get(0), planeClasses.get(1), planeClasses.get(2));
                        }
                        auxCLS.setText(flightSelection.toString());
                        observablePanelCLS.notifyObservers(auxCLS.getText());
                        observablePanelCLS.printObservers();
                        System.out.println(flightSelection);
                    }else {
                        System.out.println("Flight not flying");
                    }
                }
            }
        });
    }

    public StringBuilder getFlightSelection(){
        return flightSelection;
    }

    public JTable getDaTable(){
        return table;
    }

    public List<String> getDetailsOfFlight(){
        return flightDetails;
    }

    public List<Integer> getPlaneClasses(){
        return planeClasses;
    }

    public void sortFlightsByDateAndTimeEarliest() {
        flights = new ArrayList<>(backupFlights);
        Comparator<Flight> flightComparator = new Comparator<Flight>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            @Override
            public int compare(Flight f1, Flight f2) {
                try {
                    Date date1 = dateFormat.parse(f1.getDepartureDate());
                    Date date2 = dateFormat.parse(f2.getDepartureDate());

                    int dateComparison = date1.compareTo(date2);
                    if (dateComparison != 0) {
                        return dateComparison;
                    }

                    Date time1 = timeFormat.parse(f1.getDepartureTime());
                    Date time2 = timeFormat.parse(f2.getDepartureTime());
                    return time1.compareTo(time2);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };

        flights.sort(flightComparator);
        abstractTableModel.fireTableDataChanged();
    }

    public void sortByDateAndTimeLatest(){
        sortFlightsByDateAndTimeEarliest();
        Collections.reverse(flights);
        abstractTableModel.fireTableDataChanged();
        table.repaint();
    }

    public void isRequiredSeatClass(int requiredSeatClass) {
        flights = new ArrayList<>(backupFlights);
        Iterator<Flight> iterator = flights.iterator();

        while (iterator.hasNext()) {
            Flight flight = iterator.next();
            planeClasses = kontroler.getPlaneClasses(kontroler.getPlaneNameByID(flight.getPlane()));

            if (planeClasses.get(requiredSeatClass).equals(0)) {
                iterator.remove();
            } else {
                System.out.println(kontroler.getPlaneNameByID(flight.getPlane()) + " ima prvu klasu");
            }
        }
        abstractTableModel.fireTableDataChanged();
    }

    public List<Flight> getCurrentFlightsState(){
        return flights;
    }

    public void undoFlightListChange(List<Flight> flights) {
        this.flights = flights;
        abstractTableModel.fireTableDataChanged();
    }
}