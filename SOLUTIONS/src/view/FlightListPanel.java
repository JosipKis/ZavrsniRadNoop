package view;

import javax.swing.*;

public class FlightListPanel extends JPanel {

    private JTable table;

    public FlightListPanel(){
        super();
        initComps();
        layoutComps();
    }

    private void initComps() {
        table = new JTable();
    }

    private void layoutComps() {
        add(new JScrollPane(table));
    }
}
