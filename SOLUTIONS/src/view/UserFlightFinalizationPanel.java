package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFlightFinalizationPanel extends JPanel implements ActionListener {

    private JTextArea flightDetails;
    private JTextArea totalPrice;

    private JButton bookButton;

    private BookingListener boogkingListener;

    public UserFlightFinalizationPanel(){
        super();
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        flightDetails = new JTextArea();
        flightDetails.setPreferredSize(new Dimension(200, 80));
        flightDetails.setEditable(false);
        flightDetails.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        totalPrice = new JTextArea();
        totalPrice.setPreferredSize(new Dimension((int) flightDetails.getPreferredSize().getWidth() / 2, 20));
        totalPrice.setEditable(false);
        totalPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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

    public void setBookingListener(BookingListener boogkingListener){
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
}
