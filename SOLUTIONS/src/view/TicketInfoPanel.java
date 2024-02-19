package view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

public class TicketInfoPanel extends JPanel {

    private JTable flightInfoTable;
    private JTableHeader tableHeader;

    public TicketInfoPanel(){
        initComps();
        layoutComps();
    }

    private void initComps() {

        flightInfoTable = new JTable(10, 4);

    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(flightInfoTable, BorderLayout.CENTER);
    }

}
