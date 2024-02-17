package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private EntryPanel entryPanel;

    public MainFrame(){
        super("Mr.Kiš Flights");
        setSize(800, 600);
        setLocationRelativeTo(null);
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
                }else {
                    System.out.println("Fail");
                }
            }
        });
    }
}
