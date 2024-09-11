package controller.command;

import view.FlightListPanel;

public class IsFirstClassCommand implements Command{

    private FlightListPanel flightListPanel;

    public IsFirstClassCommand(FlightListPanel flightListPanel) {
        this.flightListPanel = flightListPanel;
    }

    @Override
    public void execute() {
        flightListPanel.isFirstClassFlight();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
