package controller.command;

import model.Flight;
import view.FlightListPanel;

import java.util.ArrayList;
import java.util.List;

public class IsBussinesClassCommand implements Command {

    private FlightListPanel flightListPanel;
    private List<Flight> flightListPreviousState;

    public IsBussinesClassCommand(FlightListPanel flightListPanel) {
        flightListPreviousState = new ArrayList<>(flightListPanel.getCurrentFlightsState());
        this.flightListPanel = flightListPanel;
    }

    @Override
    public void execute() {
        flightListPanel.isRequiredSeatClass(1);
    }

    @Override
    public void undo() {
        flightListPanel.undoFlightListChange(flightListPreviousState);
    }

    @Override
    public void redo() {
        execute();
    }
}
