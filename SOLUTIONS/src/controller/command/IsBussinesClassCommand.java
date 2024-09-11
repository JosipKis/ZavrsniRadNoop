package controller.command;

import view.FlightListPanel;

public class IsBussinesClassCommand implements Command {

    private FlightListPanel flightListPanel;

    public IsBussinesClassCommand(FlightListPanel flightListPanel) {
        this.flightListPanel = flightListPanel;
    }

    @Override
    public void execute() {
        flightListPanel.isRequiredSeatClass(1);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
