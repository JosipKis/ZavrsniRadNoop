package controller.command;

import model.Flight;
import view.FlightListPanel;

import java.util.ArrayList;
import java.util.List;

public class SortByEarliestCommand implements Command {

    private FlightListPanel tableOfFlights;
    private List<Flight> flightListPreviousState;

    public SortByEarliestCommand(FlightListPanel tableOfFlights) {
        this.tableOfFlights = tableOfFlights;
    }

    @Override
    public void execute() {
        flightListPreviousState = new ArrayList<>(tableOfFlights.getCurrentFlightsState());
        tableOfFlights.sortFlightsByDateAndTimeEarliest();
    }

    @Override
    public void undo() {
        tableOfFlights.undoFlightListChange(flightListPreviousState);
    }

    @Override
    public void redo() {
        execute();
    }
}