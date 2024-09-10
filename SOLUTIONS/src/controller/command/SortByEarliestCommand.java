package controller.command;

import view.FlightListPanel;

public class SortByEarliestCommand implements Command {

    private FlightListPanel tableOfFlights;

    public SortByEarliestCommand(FlightListPanel tableOfFlights) {
        this.tableOfFlights = tableOfFlights;
    }

    @Override
    public void execute() {
        tableOfFlights.sortFlightsByDateAndTime();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}