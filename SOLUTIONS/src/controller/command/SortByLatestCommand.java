package controller.command;

import model.Flight;
import view.FlightListPanel;

import java.util.ArrayList;
import java.util.List;

public class SortByLatestCommand implements Command {

    private FlightListPanel flightListPanel;
    private List<Flight> flightListPreviousState;

    public SortByLatestCommand(FlightListPanel flightListPanel) {
        this.flightListPanel = flightListPanel;
    }

    @Override
    public void execute() {
        flightListPreviousState = new ArrayList<>(flightListPanel.getCurrentFlightsState());
        flightListPanel.sortByDateAndTimeLatest();
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