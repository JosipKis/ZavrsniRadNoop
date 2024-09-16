package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

    }

    // Stvaranje zrakoplova u admin panelu
    public void setPlaneCreationListener(PlaneCreationListener listener) {
        createPlanePanel.setPlaneCreationListener(listener);
    }

    public List<String> getNewPlaneSpecs(){
        return createPlanePanel.getNewPlaneSpecs();
    }

    public void resetCreateFlightPanelForm() {
        createPlanePanel.resetForm();
    }

    // Stvaranje letova u admin panelu
    public void setFlightCreationListener(SingleButtonOnPanelListener listener) {
        createFlightPanel.setBookingListener(listener);
    }

    public List<String> getNewFlightSpecs(){
        return createFlightPanel.getNewFlightSpecs();
    }

    public void resetCreateFlightPanel(){
        createFlightPanel.resetCreateFlightForm();
    }
}