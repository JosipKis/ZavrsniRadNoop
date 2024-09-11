package controller.command;

import view.FlightListPanel;

public class IsEconomyClassCommand implements Command {

    private FlightListPanel flightListPanel;

    public IsEconomyClassCommand(FlightListPanel flightListPanel){
        this.flightListPanel = flightListPanel;
    }

    @Override
    public void execute() {
        flightListPanel.isRequiredSeatClass(2);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
