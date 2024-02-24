package view;

import model.Flights;
import model.FlightsINT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TicketOptionsPanel extends JPanel implements ActionListener {

    private JButton calculatePriceBtn;
    private JTextField priceDisplay;
    private JButton buyTicketBtn;
    private JRadioButton fstCls;
    private JRadioButton sndCls;
    private JRadioButton trdCls;
    private ButtonGroup flightClasses;
    private JCheckBox handBaggageCb;
    private JCheckBox cargoBg;
    private JCheckBox addBaggage;
    private JComboBox<FlightsINT> flightComboBox;
    private List<FlightsINT> list;

    private FlightsINT chosenFlight;
    private ArrayList<String> carriedBaggage;

    private TicketOptionsListener ticketOptionsListener;
    private FlightCBListener flightCBListener;
    private TicketFinalPriceCalculator ticketFinalPriceCalculator;

    public TicketOptionsPanel(){
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        calculatePriceBtn = new JButton("Calculate Price");
        calculatePriceBtn.setActionCommand("calculate");
        buyTicketBtn = new JButton("Book flight");
        buyTicketBtn.setActionCommand("book");
        priceDisplay = new JTextField(10);
        priceDisplay.setEditable(false);
        fstCls =  new JRadioButton("First");
        fstCls.setActionCommand("fstCls");
        sndCls = new JRadioButton("Business");
        sndCls.setActionCommand("sndCls");
        trdCls = new JRadioButton("Economy");
        trdCls.setActionCommand("trdCls");
        flightClasses = new ButtonGroup();
        flightClasses.add(fstCls);
        flightClasses.add(sndCls);
        flightClasses.add(trdCls);
        flightClasses.setSelected(trdCls.getModel(), true);
        handBaggageCb = new JCheckBox("Hand Carry");
        cargoBg = new JCheckBox("Large Cargo");
        addBaggage = new JCheckBox("Additional baggage");
        list = new ArrayList<>();
        list = fillFlightList();
        flightComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
        for (FlightsINT item : list) {
            flightComboBox.addItem(item);
        }
        flightComboBox.setSelectedItem(null);
        flightComboBox.setPreferredSize(new Dimension(150, 25));

    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy++;
        add(new JLabel("Flights"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 20);
        add(flightComboBox, gbc);
        gbc.gridy = 0;
        gbc.gridx++;

        gbc.insets = new Insets(0, 0, 0, 0);
        add(new JLabel("Flight class:"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 30);
        add(fstCls, gbc);
        gbc.gridy++;
        add(sndCls, gbc);
        gbc.gridy++;
        add(trdCls, gbc);
        gbc.gridy = 0;
        gbc.gridx++;

        gbc.insets = new Insets(0, 0, 0, 0);
        add(new JLabel("Baggage: "), gbc);
        gbc.gridy++;
        add(handBaggageCb, gbc);
        gbc.gridy++;
        add(cargoBg, gbc);
        gbc.gridy++;
        add(addBaggage, gbc);
        gbc.gridy = 0;
        gbc.gridx++;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,50 ,0, 0);
        add(calculatePriceBtn, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(10,50 ,0, 0);
        add(new JLabel("Price"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0,50 ,20, 0);
        add(priceDisplay, gbc);
        gbc.gridy++;

        gbc.insets = new Insets(0,50 ,0, 0);
        add(buyTicketBtn, gbc);

    }



    private void activatePanel() {
        buyTicketBtn.addActionListener(this);
        calculatePriceBtn.addActionListener(this);
        flightComboBox.addActionListener(this);
        fstCls.addActionListener(this);
        sndCls.addActionListener(this);
        trdCls.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ticketOptionsListener != null){
            priceDisplay.setText("");
            selectedBaggage();
            ticketOptionsListener.btnClicked(ae.getActionCommand());
            if (flightCBListener != null){
                for (int c = 0; c < list.size(); c++){
                    if (Objects.equals(flightComboBox.getSelectedItem(), list.get(c))){
                        chosenFlight = list.get(c);
                        System.out.println(list.get(c));
                        flightCBListener.checkBoxOptionSelected(list.get(c));
                    }
                }
            }
        }
    }

    public void setTicketOptionsListener(TicketOptionsListener ticketOptionsListener) {
        this.ticketOptionsListener = ticketOptionsListener;
    }

    public void setFlightCBListener(FlightCBListener flightCBListener) {
        this.flightCBListener = flightCBListener;
    }

    private ArrayList<FlightsINT> fillFlightList(){
        ArrayList<FlightsINT> arrayList = new ArrayList<>();
        FlightsINT flight1 = new Flights("Boeing 737", "Luxenburg", 299.99f);
        FlightsINT flight2 = new Flights("Hawker Harrier", "London", 9999.99f);
        arrayList.add(flight1);
        arrayList.add(flight2);

        return arrayList;
    }

    private void selectedBaggage(){
        carriedBaggage = new ArrayList<>();
        boolean handBaggageSelected = handBaggageCb.isSelected();
        boolean cargoSelected = cargoBg.isSelected();
        boolean addBaggageSelected = addBaggage.isSelected();
        if (handBaggageSelected){
            carriedBaggage.add("handBaggageCb");
        }
        if (cargoSelected){
            carriedBaggage.add("cargoBg");
        }
        if (addBaggageSelected){
            carriedBaggage.add("addBaggage");
        }
    }

    public void calculatePrice(){
        ticketFinalPriceCalculator = new TicketFinalPriceCalculator(chosenFlight.getPrice(), flightClasses.getSelection().getActionCommand(), carriedBaggage);
        System.out.println(chosenFlight.getPrice());
        System.out.println(flightClasses.getSelection().getActionCommand());
        System.out.println(Arrays.toString(carriedBaggage.toArray()));
        priceDisplay.setText(ticketFinalPriceCalculator.getTotalPrice());
    }
}
