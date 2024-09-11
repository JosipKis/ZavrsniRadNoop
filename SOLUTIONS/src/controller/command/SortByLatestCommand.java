package controller.command;

import view.FlightListPanel;

public class SortByLatestCommand implements Command {

    private FlightListPanel flightListPanel;

    public SortByLatestCommand(FlightListPanel flightListPanel) {
        this.flightListPanel = flightListPanel;
    }

    @Override
    public void execute() {
        flightListPanel.sortByDateAndTimeLatest();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}