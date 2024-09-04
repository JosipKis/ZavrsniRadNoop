package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiptButtonsPanel extends JPanel implements ActionListener {

    private JButton print2PDFBtn;
    private JButton confirmBtn;

    private ReceiptButtonsListener listener;

    public ReceiptButtonsPanel(){
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        print2PDFBtn = new JButton("PDF karte");
        print2PDFBtn.setActionCommand("print2PDF");

        confirmBtn = new JButton("Potvrdi");
        confirmBtn.setActionCommand("confirm");
        confirmBtn.setPreferredSize(print2PDFBtn.getPreferredSize());
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 0, 0);

        add(confirmBtn, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);

        add(print2PDFBtn, gbc);
    }

    private void activateComps() {
        print2PDFBtn.addActionListener(this);
        confirmBtn.addActionListener(this);
    }

    public void setListener(ReceiptButtonsListener listener){
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener != null){
            switch (e.getActionCommand()){
                case "print2PDF":
                    listener.print2PDF();
                    break;
                case "confirm":
                    listener.confirm();
                    break;
            }
        }
    }
}
