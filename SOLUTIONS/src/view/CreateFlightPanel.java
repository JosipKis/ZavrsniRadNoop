package view;

import com.toedter.calendar.JDateChooser;
import controller.Kontroler;
import model.Flight;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateFlightPanel extends JPanel {

    private JComboBox<String> planes;
    private JTextField departureField;
    private JTextField destinationField;
    private JSpinner departureTimeSpinner;
//    private JCalendar dateField;
    private JDateChooser dateChooser;

    private JButton createFlightButton;

    private List<Flight> allFlights;
    private String[] planeNames;

    private Kontroler kontroler;

    public CreateFlightPanel() {
        initComps();
        layoutComps();
    }

    private void initComps() {
        kontroler = new Kontroler();
        kontroler.connectToDatabase();

        allFlights = kontroler.getAllFlights();

        planeNames = new String[allFlights.size()];
        for (int i = 0; i < allFlights.size(); i++) {
            planeNames[i] = kontroler.getPlaneNameByID(allFlights.get(i).getPlane());
            System.out.println("start" + kontroler.getPlaneNameByID(allFlights.get(i).getPlane()));
        }

        planes = new JComboBox<>(planeNames);
        departureField = new JTextField(10);
        destinationField = new JTextField(10);
        departureTimeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(departureTimeSpinner, "HH:mm:ss");
        departureTimeSpinner.setEditor(timeEditor);
        departureTimeSpinner.setValue(new Date());
        departureTimeSpinner.setPreferredSize(new Dimension(departureField.getPreferredSize().width, departureField.getPreferredSize().height));
//        dateField = new JCalendar();
//        dateField.setPreferredSize(new Dimension(departureField.getPreferredSize().width, departureField.getPreferredSize().height));
        planes.setPreferredSize(new Dimension(departureField.getPreferredSize().width, planes.getPreferredSize().height));

        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 0);
        dateChooser = new JDateChooser(c.getTime());
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setPreferredSize(new Dimension(departureField.getPreferredSize().width, departureField.getPreferredSize().height));

        createFlightButton = new JButton("Dodaj let");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,0,5,0);

        add(new JLabel("Izberi avion:"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(planes, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Datum: "), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(dateChooser, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Vrijeme polaska: "), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(departureTimeSpinner, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Polazište: "), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(departureField, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Odredište: "), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(destinationField, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15,0,0,0);

        add(createFlightButton, gbc);
    }
}