package view;

import controller.Kontroler;
import model.Flight;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FlightListPanel extends JPanel {

    private JTable table;

    private Kontroler kontroler;

    private List<Flight> flights;

    public FlightListPanel(){
        super();
        initComps();
        layoutComps();
    }

    private void initComps() {
        table = new JTable();
        kontroler = new Kontroler();

        try {
            kontroler.connectToDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            flights = kontroler.getAllFlights();

            for (Flight flight : flights){
                System.out.println(flight);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void layoutComps() {
        add(new JScrollPane(table));
    }
}
