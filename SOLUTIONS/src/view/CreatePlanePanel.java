package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreatePlanePanel extends JPanel implements ActionListener {

    private JTextField planeName;
    private JTextField planeManufacturer;
    private JTextField firstClassPrice;
    private JTextField businessClassPrice;
    private JTextField economyClassPrice;

    private JCheckBox hasFirstClass;
    private JCheckBox hasBusinessClass;
    private JCheckBox hasEconomyClass;
    private boolean firstClassSelected;
    private boolean businessClassSelected;
    private boolean economyClassSelected;

    private JButton createPlaneBtn;

    private List<String> newPlaneSpecs;

    private PlaneCreationListener planeCreationListener;

    public CreatePlanePanel() {
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        planeName = new JTextField(10);
        planeName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        planeManufacturer = new JTextField(10);
        planeManufacturer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        firstClassPrice = new JTextField(10);
        firstClassPrice.setBorder(BorderFactory.createEtchedBorder());
        firstClassPrice.setEnabled(false);
        firstClassSelected = false;
        businessClassPrice = new JTextField(10);
        businessClassPrice.setBorder(BorderFactory.createEtchedBorder());
        businessClassPrice.setEnabled(false);
        businessClassSelected = false;
        economyClassPrice = new JTextField(10);
        economyClassPrice.setBorder(BorderFactory.createEtchedBorder());
        economyClassPrice.setEnabled(false);
        economyClassSelected = false;

        hasFirstClass = new JCheckBox("Prvi razred?");
        hasFirstClass.setActionCommand("fst");
        hasBusinessClass = new JCheckBox("Poslovni razred?");
        hasBusinessClass.setActionCommand("bsn");
        hasEconomyClass = new JCheckBox("Ekonomični razred?");
        hasEconomyClass.setActionCommand("eco");

        createPlaneBtn = new JButton("Dodaj Avion");
        createPlaneBtn.setActionCommand("createPlane");

        newPlaneSpecs = new ArrayList<>();
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,0,5,0);

        add(new JLabel("Ime Aviona:"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(planeName, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Proizvođač:"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(planeManufacturer, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;

        add(hasFirstClass, gbc);
        gbc.gridy++;

        add(hasBusinessClass, gbc);
        gbc.gridy++;

        add(hasEconomyClass, gbc);
        gbc.gridy++;

        add(new JLabel("Cijena Prvog Razreda:"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(firstClassPrice, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Cijena Posl. Razreda:"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(businessClassPrice, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Cijena Eko. Razreda:"), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;

        add(economyClassPrice, gbc);
        gbc.gridy++;
        gbc.gridx--;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15,0,0,0);

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
                newPlaneSpecs.clear();
                newPlaneSpecs.add(planeName.getText());
                newPlaneSpecs.add(planeManufacturer.getText());
                checkForInteger(firstClassPrice.getText(), businessClassPrice.getText(), economyClassPrice.getText());

                planeCreationListener.createBtnPressed();
            } else {
                planeCreationListener.checkBoxSelected(e.getActionCommand());
                enableTextFields(e.getActionCommand());
            }
        }
    }

    private void enableTextFields(String value){
        switch (value) {
            case "fst":
                if (firstClassSelected){
                    firstClassPrice.setEnabled(false);
                    firstClassPrice.setBorder(BorderFactory.createEtchedBorder());
                    firstClassSelected = false;
                } else {
                    firstClassPrice.setEnabled(true);
                    firstClassPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    firstClassSelected = true;
                }
                break;
            case "bsn":
                if (businessClassSelected){
                    businessClassPrice.setEnabled(false);
                    businessClassPrice.setBorder(BorderFactory.createEtchedBorder());
                    businessClassSelected = false;
                } else {
                    businessClassPrice.setEnabled(true);
                    businessClassPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    businessClassSelected = true;
                }
                break;
            case "eco":
                if (economyClassSelected){
                    economyClassPrice.setEnabled(false);
                    economyClassPrice.setBorder(BorderFactory.createEtchedBorder());
                    economyClassSelected = false;
                } else {
                    economyClassPrice.setEnabled(true);
                    economyClassPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    economyClassSelected = true;
                }
                break;
        }
    }

    private void checkForInteger(String value1, String value2, String value3){
        try {
            if (!Objects.equals(value1, "")){
                Integer.parseInt(value1);
            }else {
                value1 = "0";
            }
            if (!Objects.equals(value2, "")){
                Integer.parseInt(value2);
            }else {
                value2 = "0";
            }
            if (!Objects.equals(value3, "")){
                Integer.parseInt(value3);
            }else {
                value3 = "0";
            }

            newPlaneSpecs.add(value1);
            newPlaneSpecs.add(value2);
            newPlaneSpecs.add(value3);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Unesena cijena razreda mora sadržavati samo brojčane znamenke!", "Nepravilna cijena", JOptionPane.ERROR_MESSAGE);
        }

    }

    public List<String> getNewPlaneSpecs() {
        return newPlaneSpecs;
    }
}