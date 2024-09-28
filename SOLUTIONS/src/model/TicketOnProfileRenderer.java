package model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TicketOnProfileRenderer extends JLabel implements ListCellRenderer<String> {

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        URL imageURL = getClass().getResource("icons/ticket44_20.jpg");
        ImageIcon icon = new ImageIcon("SOLUTIONS/src/view/icons/ticket44_20.jpg");

        setIcon(icon);
        setText(value);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setOpaque(true);

        return this;
    }
}