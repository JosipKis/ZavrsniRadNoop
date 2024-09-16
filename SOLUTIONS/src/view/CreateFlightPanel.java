package view;

import com.toedter.calendar.JDateChooser;
import controller.Kontroler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CreateFlightPanel extends JPanel implements ActionListener {

    private JComboBox<String> planes;
    private JTextField departureField;
    private JTextField destinationField;
    private JSpinner departureTimeSpinner;
//    private JCalendar dateField;
    private JDateChooser dateChooser;

    private SimpleDateFormat sdf;
    private SimpleDateFormat timeFormat;

    private JButton createFlightButton;

    private List<String> allFlights;
    private String[] planeData;
    private String[] planeNames;
    private String[] planeIDs;
    private List<String> newFlightSpecs;

    private Kontroler kontroler;
    private SingleButtonOnPanelListener singleButtonOnPanelListener;

    public CreateFlightPanel() {
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        kontroler = Kontroler.getInstance();
        kontroler.connectToDatabase();

        allFlights = kontroler.getAllUnassignedPlaneNames();
        newFlightSpecs = new ArrayList<>();

        planeData = new String[allFlights.size()];
        planeNames = new String[allFlights.size()];
        planeIDs = new String[allFlights.size()];

        for (int i = 0; i < allFlights.size(); i++) {
            planeData[i] = kontroler.getAllUnassignedPlaneNames().get(i);
            System.out.println("start" + kontroler.getAllUnassignedPlaneNames().get(i));
        }

        for (int i = 0; i < allFlights.size(); i++) {
            planeNames[i] = planeData[i].split(",")[0];
            planeIDs[i] = planeData[i].split(",")[1];
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

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        timeFormat = new SimpleDateFormat("HH:mm:ss");

        createFlightButton = new JButton("Dodaj let");
        createFlightButton.setActionCommand("createFlight");
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

    private void activateComps() {
        createFlightButton.addActionListener(this);
    }

    public void setBookingListener(SingleButtonOnPanelListener listener) {
        this.singleButtonOnPanelListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (singleButtonOnPanelListener != null && !departureField.getText().isEmpty() && !destinationField.getText().isEmpty()) {
                newFlightSpecs.clear();

                newFlightSpecs.add(planeIDs[planes.getSelectedIndex()]);
                newFlightSpecs.add(departureField.getText());
                newFlightSpecs.add(destinationField.getText());

                String dateOfTakeOff = sdf.format(dateChooser.getDate());
                String timeOfTakeOff = timeFormat.format(departureTimeSpinner.getValue());

                newFlightSpecs.add(dateOfTakeOff + " " + timeOfTakeOff);

                singleButtonOnPanelListener.bookButtonClicked(e.getActionCommand());
            }else {
                JOptionPane.showMessageDialog(null, "Niste unijeli sva polja!", "Nedostaju podaci", JOptionPane.ERROR_MESSAGE);
            }
        }catch (NullPointerException npe){
            JOptionPane.showMessageDialog(null, "Datum nije u pravilnom formatu! (yyyy-MM-dd)", "Nepravilan format datuma", JOptionPane.ERROR_MESSAGE);
        }

    }

    public List<String> getNewFlightSpecs() {
        return newFlightSpecs;
    }

    public void resetCreateFlightForm(){
        departureField.setText("");
        destinationField.setText("");
        planes.requestFocus();
    }
}