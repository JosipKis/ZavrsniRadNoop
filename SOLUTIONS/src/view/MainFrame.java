package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private EntryPanel entryPanel;

    public MainFrame(){
        super("Mr.Kiš Flights");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        initComps();
        layoutComps();
        activateMainFrame();
    }

    private void initComps() {
        entryPanel = new EntryPanel();
    }

    private void layoutComps() {
        add(entryPanel);
    }

    private void activateMainFrame() {
        entryPanel.setEntryPanelListener(new EntryPanelListener() {
            @Override
            public void buttonPressed(String buttonName) {
                if (buttonName.equals("customer")){
                    System.out.println("Success");
                    dispose();
                    new TicketPurchaseFrame();
                }else if (buttonName.equals("admin")){

                    System.out.println("Fail");
                }
            }
        });
    }
}
