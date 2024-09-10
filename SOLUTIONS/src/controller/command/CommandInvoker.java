package controller.command;

import java.util.Stack;

public class CommandInvoker {

    private Command command;
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public CommandInvoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void runCommand(Command command){
        this.command = command;
        command.execute();
        undoStack.push(command);
        getStacksStatus();
    }

    public void undoCommand(){
        if (undoStack.isEmpty()){
            System.out.println("Nothing to undo!!");
        }else {
            this.command = undoStack.pop();
            this.command.undo();
            redoStack.push(command);
            getStacksStatus();
        }
    }

    public void redoCommand(){
        if (redoStack.isEmpty()){
            System.out.println("Nothing to redo!");
        }else {
            this.command = redoStack.pop();
            this.command.redo();
            undoStack.push(command);
            getStacksStatus();
        }
    }

    private void getStacksStatus(){
        System.out.println("Undo stack:\n");
        System.out.println(undoStack);
        System.out.println("Redo stack:\n");
        System.out.println(redoStack);
    }
}