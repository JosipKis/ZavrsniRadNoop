package view;

import controller.Kontroler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserFlightOptionsPanel extends JPanel implements ActionListener {

    private JComboBox<String> flightsComboBox;
    private String[] comboBoxContents;
    private JCheckBox handLuggage;
    private JCheckBox checkedLuggage;

    private JRadioButton firstClass;
    private JRadioButton businessClass;
    private JRadioButton economyClass;
    private ButtonGroup buttonGroup;

    private int totalMoneyToPay = 0;
    private boolean isFirstClassPriceApplied = false;
    private boolean isBusinessClassPriceApplied = false;
    private boolean isEconomyClassPriceApplied = false;

    private ComboBoxListener comboBoxListener;

    public UserFlightOptionsPanel(){
        super();
        initComps();
        layoutComps();
    }

    private void initComps() {
        comboBoxContents = new String[]{"Odaberite...", "Najstarije gore", "Najnovije gore", "Prva klasa", "Poslovna klasa", "Ekonomična klasa"};
        flightsComboBox = new JComboBox<>(comboBoxContents);
        flightsComboBox.setPreferredSize(new Dimension(100, 30));
        flightsComboBox.addActionListener(this);

        handLuggage = new JCheckBox("Hand Luggage");
        handLuggage.setEnabled(false);
        checkedLuggage = new JCheckBox("Checked Luggage");
        checkedLuggage.setEnabled(false);

        firstClass = new JRadioButton("First Class");
        firstClass.setActionCommand("first");
        firstClass.setEnabled(false);
        businessClass = new JRadioButton("Business Class");
        businessClass.setActionCommand("bussy");
        businessClass.setEnabled(false);
        economyClass = new JRadioButton("Economy Class");
        economyClass.setActionCommand("eco");
        economyClass.setEnabled(false);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(firstClass);
        buttonGroup.add(businessClass);
        buttonGroup.add(economyClass);
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        add(flightsComboBox, gbc);
        gbc.gridx++;
        gbc.insets = new Insets(0, 0, 0, 15);

        add(handLuggage, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 2, 0, 0);

        add(checkedLuggage, gbc);
        gbc.gridy = 0;
        gbc.gridx++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(firstClass, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 23, 5, 0);

        add(businessClass, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 27, 0, 0);

        add(economyClass, gbc);
    }

    public void activatePanel(UserPanel userPanel) {
        firstClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (firstClass.isSelected()){
                    if (isBusinessClassPriceApplied){
                        isBusinessClassPriceApplied = false;
                        totalMoneyToPay -= Kontroler.getClassPrices().get(1);
                        userPanel.setTotalPriceText(totalMoneyToPay + " €");
                    }else if (isEconomyClassPriceApplied){
                        isEconomyClassPriceApplied = false;
                        totalMoneyToPay -= Kontroler.getClassPrices().get(2);
                        userPanel.setTotalPriceText(totalMoneyToPay + " €");
                    }

                    if (!isFirstClassPriceApplied){
                        System.out.println(Kontroler.getClassPrices().get(0));
                        totalMoneyToPay += Kontroler.getClassPrices().get(0);
                        userPanel.setTotalPriceText(totalMoneyToPay + " €");
                        isFirstClassPriceApplied = true;
                    }
                }
            }
        });

        businessClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (businessClass.isSelected()){
                    if (isFirstClassPriceApplied){
                        isFirstClassPriceApplied = false;
                        totalMoneyToPay -= Kontroler.getClassPrices().get(0);
                        userPanel.setTotalPriceText(totalMoneyToPay + " €");
                    }else if (isEconomyClassPriceApplied){
                        isEconomyClassPriceApplied = false;
                        totalMoneyToPay -= Kontroler.getClassPrices().get(2);
                        userPanel.setTotalPriceText(totalMoneyToPay + " €");
                    }

                    if (!isBusinessClassPriceApplied){
                        System.out.println(Kontroler.getClassPrices().get(1));
                        totalMoneyToPay += Kontroler.getClassPrices().get(1);
                        userPanel.setTotalPriceText(totalMoneyToPay + " €");
                        isBusinessClassPriceApplied = true;
                    }
                }
            }
        });

        economyClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isBusinessClassPriceApplied){
                    isBusinessClassPriceApplied = false;
                    totalMoneyToPay -= Kontroler.getClassPrices().get(1);
                    userPanel.setTotalPriceText(totalMoneyToPay + " €");
                }else if (isFirstClassPriceApplied) {
                    isFirstClassPriceApplied = false;
                    totalMoneyToPay -= Kontroler.getClassPrices().get(0);
                    userPanel.setTotalPriceText(totalMoneyToPay + " €");
                }

                if (!isEconomyClassPriceApplied){
                    System.out.println(Kontroler.getClassPrices().get(2));
                    totalMoneyToPay += Kontroler.getClassPrices().get(2);
                    userPanel.setTotalPriceText(totalMoneyToPay + " €");
                    isEconomyClassPriceApplied = true;
                }
            }
        });

        handLuggage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (handLuggage.isSelected()){
                    System.out.println("20");
                    totalMoneyToPay += 20;
                    userPanel.setTotalPriceText(totalMoneyToPay + " €");
                }else {
                    totalMoneyToPay -= 20;
                    userPanel.setTotalPriceText(totalMoneyToPay + " €");
                }
            }
        });

        checkedLuggage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkedLuggage.isSelected()){
                    System.out.println("70");
                    totalMoneyToPay += 70;
                    userPanel.setTotalPriceText(totalMoneyToPay + " €");
                }else {
                    totalMoneyToPay -= 70;
                    userPanel.setTotalPriceText(totalMoneyToPay + " €");
                }
            }
        });
    }

    public String getSelectedFlightClass(){
        return buttonGroup.getSelection().getActionCommand();
    }

    public void setEnableOptions(int first, int second, int third){
        handLuggage.setEnabled(true);
        checkedLuggage.setEnabled(true);

        if (first != 0){
            firstClass.setEnabled(true);
        }

        if (second != 0){
            businessClass.setEnabled(true);
        }

        if (third != 0){
            economyClass.setEnabled(true);
        }
        System.out.println("First: " + first + ", Second: " + second + ", Third: " + third);
    }

    public void disableOptions(){
        handLuggage.setEnabled(false);
        handLuggage.setSelected(false);
        checkedLuggage.setEnabled(false);
        checkedLuggage.setSelected(false);
        firstClass.setEnabled(false);
        firstClass.setSelected(false);
        businessClass.setEnabled(false);
        businessClass.setSelected(false);
        economyClass.setEnabled(false);
        economyClass.setSelected(false);

        buttonGroup.clearSelection();

        isFirstClassPriceApplied = false;
        isBusinessClassPriceApplied = false;
        isEconomyClassPriceApplied = false;
    }

    public boolean getIsAClassSelected() {
        if (isFirstClassPriceApplied || isBusinessClassPriceApplied || isEconomyClassPriceApplied){
            return true;
        }else {
            return false;
        }
    }

    public void setTotalMoneyToPay(int totalMoneyToPay) {
        this.totalMoneyToPay = totalMoneyToPay;
    }

    public void setComboBoxListener(ComboBoxListener comboBoxListener){
        this.comboBoxListener = comboBoxListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBoxListener != null){
            comboBoxListener.comboBoxPressed(String.valueOf(flightsComboBox.getSelectedItem()));
        }
    }
}
