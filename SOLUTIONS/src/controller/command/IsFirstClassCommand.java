package controller.command;

import view.FlightListPanel;

public class IsFirstClassCommand implements Command{

    private FlightListPanel flightListPanel;

    public IsFirstClassCommand(FlightListPanel flightListPanel) {
        this.flightListPanel = flightListPanel;
    }

    @Override
    public void execute() {
        flightListPanel.isRequiredSeatClass(0);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
