package view;

import model.observer.AuxCLS;
import model.observer.ObservablePanel;
import model.observer.RegistrationInt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFlightFinalizationPanel extends JPanel implements ActionListener, RegistrationInt {

    private JTextArea flightDetails;
    private JTextField totalPrice;

    private JButton bookButton;

    private SingleButtonOnPanelListener boogkingListener;

    private AuxCLS auxCLS;

    public UserFlightFinalizationPanel(){
        super();
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        auxCLS = AuxCLS.getInstance();

        flightDetails = new JTextArea();
        flightDetails.setPreferredSize(new Dimension(200, 80));
        flightDetails.setEditable(false);
        flightDetails.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        totalPrice = new JTextField();
        totalPrice.setPreferredSize(new Dimension((int) flightDetails.getPreferredSize().getWidth() / 2, 20));
        totalPrice.setEditable(false);
        totalPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalPrice.setHorizontalAlignment(JTextField.CENTER);
        totalPrice.setText("0 €");

        bookButton = new JButton("Book");
        bookButton.setActionCommand("book");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(flightDetails, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);

        add(new JLabel("Ukupna cijena:"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(totalPrice, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);

        add(bookButton, gbc);
    }

    private void activatePanel() {
        bookButton.addActionListener(this);
    }

    public void setBookingListener(SingleButtonOnPanelListener boogkingListener){
        this.boogkingListener = boogkingListener;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (boogkingListener != null){
            boogkingListener.bookButtonClicked(ae.getActionCommand());
        }
    }

    public void setText(String text){
        flightDetails.setText(text);
    }

    public String getText(){
        return flightDetails.getText();
    }

    public void setTotalPrice(String text){
        totalPrice.setText(text);
    }

    public String getTotalPrice(){
        return totalPrice.getText();
    }

    @Override
    public void registerObserver(ObservablePanel observer) {
        observer.addObserver(this);
    }

    @Override
    public void update(String text) {
        System.out.println("Notification successful");
        String[] data = auxCLS.getText().split(",");
        flightDetails.setText(" " +data[0]);
        flightDetails.append("\n" + data[1]);
        flightDetails.append("\n" + data[2]);
        flightDetails.append("\n" + data[3]);
        flightDetails.append("\n" + data[4]);
    }
}
