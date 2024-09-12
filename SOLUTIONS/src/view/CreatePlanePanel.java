package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePlanePanel extends JPanel implements ActionListener {

    private JTextField planeName;
    private JTextField planeManufacturer;
    private JTextField firstClassPrice;
    private JTextField businessClassPrice;
    private JTextField economyClassPrice;

    private JCheckBox hasFirstClass;
    private JCheckBox hasBusinessClass;
    private JCheckBox hasEconomyClass;

    private JButton createPlaneBtn;

    private PlaneCreationListener planeCreationListener;

    public CreatePlanePanel() {
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        planeName = new JTextField();
        planeManufacturer = new JTextField();
        firstClassPrice = new JTextField();
        businessClassPrice = new JTextField();
        economyClassPrice = new JTextField();

        hasFirstClass = new JCheckBox("Prvi razred?");
        hasFirstClass.setActionCommand("fst");
        hasBusinessClass = new JCheckBox("Poslovni razred?");
        hasBusinessClass.setActionCommand("bsn");
        hasEconomyClass = new JCheckBox("Ekonomični razred?");
        hasEconomyClass.setActionCommand("eco");

        createPlaneBtn = new JButton("Dodaj Avion");
        createPlaneBtn.setActionCommand("createPlane");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(new JLabel("Ime Aviona:"), gbc);
        gbc.gridx++;

        add(planeName, gbc);
        gbc.gridy++;
        gbc.gridx--;

        add(new JLabel("Proizvođač:"), gbc);
        gbc.gridx++;

        add(planeManufacturer, gbc);
        gbc.gridy++;
        gbc.gridx--;

        add(hasFirstClass, gbc);
        gbc.gridy++;

        add(hasBusinessClass, gbc);
        gbc.gridy++;

        add(hasEconomyClass, gbc);
        gbc.gridy++;

        add(new JLabel("Cijena Prvog Razreda:"), gbc);
        gbc.gridx++;

        add(firstClassPrice, gbc);
        gbc.gridy++;
        gbc.gridx--;

        add(new JLabel("Cijena Posl. Razreda:"), gbc);
        gbc.gridx++;

        add(businessClassPrice, gbc);
        gbc.gridy++;
        gbc.gridx--;

        add(new JLabel("Cijena Eko. Razreda:"), gbc);
        gbc.gridx++;

        add(economyClassPrice, gbc);
        gbc.gridy++;
        gbc.gridx--;

        add(createPlaneBtn, gbc);
    }

    private void activateComps() {
        hasFirstClass.addActionListener(this);
        hasBusinessClass.addActionListener(this);
        hasEconomyClass.addActionListener(this);

        createPlaneBtn.addActionListener(this);
    }

    public void setPlaneCreationListener(PlaneCreationListener planeCreationListener) {
        this.planeCreationListener = planeCreationListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (planeCreationListener != null){
            if (e.getActionCommand().equals("createPlane")){
                planeCreationListener.createBtnPressed();
            } else {
                planeCreationListener.checkBoxSelected(e.getActionCommand());
            }
        }
    }
}