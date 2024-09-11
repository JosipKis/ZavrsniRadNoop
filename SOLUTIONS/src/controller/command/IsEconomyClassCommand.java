package controller.command;

import model.Flight;
import view.FlightListPanel;

import java.util.ArrayList;
import java.util.List;

public class IsEconomyClassCommand implements Command {

    private FlightListPanel flightListPanel;
    private List<Flight> flightListPreviousState;

    public IsEconomyClassCommand(FlightListPanel flightListPanel){
        this.flightListPanel = flightListPanel;
    }

    @Override
    public void execute() {
        flightListPreviousState = new ArrayList<>(flightListPanel.getCurrentFlightsState());
        flightListPanel.isRequiredSeatClass(2);
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
