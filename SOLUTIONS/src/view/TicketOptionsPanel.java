package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private TicketOptionsListener ticketOptionsListener;


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
        sndCls = new JRadioButton("Business");
        trdCls = new JRadioButton("Economy");
        flightClasses = new ButtonGroup();
        flightClasses.add(fstCls);
        flightClasses.add(sndCls);
        flightClasses.add(trdCls);
        flightClasses.setSelected(trdCls.getModel(), true);
        handBaggageCb = new JCheckBox("Hand Carry");
        cargoBg = new JCheckBox("Large Cargo");
        addBaggage = new JCheckBox("Additional baggage");


    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;

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
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ticketOptionsListener != null){
            ticketOptionsListener.btnClicked(ae.getActionCommand());
        }
    }

    public void setTicketOptionsListener(TicketOptionsListener ticketOptionsListener) {
        this.ticketOptionsListener = ticketOptionsListener;
    }
}
