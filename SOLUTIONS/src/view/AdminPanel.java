package view;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    private CreatePlanePanel createPlanePanel;
    private CreateFlightPanel createFlightPanel;

    public AdminPanel() {
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        createPlanePanel = new CreatePlanePanel();
        createPlanePanel.setPreferredSize(new Dimension(300, 300));
        createPlanePanel.setBorder(BorderFactory.createTitledBorder("Dodavanje aviona"));

        createFlightPanel = new CreateFlightPanel();
        createFlightPanel.setPreferredSize(new Dimension(300, 300));
        createFlightPanel.setBorder(BorderFactory.createTitledBorder("Dodavanje leta"));
    }

    private void layoutComps() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(createPlanePanel);
        add(createFlightPanel);
    }

    private void activatePanel() {
        createPlanePanel.setPlaneCreationListener(new PlaneCreationListener() {
            @Override
            public void createBtnPressed() {
                System.out.println("Create button pressed");
            }

            @Override
            public void checkBoxSelected(String actionCommand) {
                switch (actionCommand) {
                    case "fst":
                        System.out.println("First class selected");
                        break;
                    case "bsn":
                        System.out.println("Business class selected");
                        break;
                    case "eco":
                        System.out.println("Economy class selected");
                        break;
                }
            }
        });
    }
}