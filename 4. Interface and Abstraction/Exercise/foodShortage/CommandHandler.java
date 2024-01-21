package foodShortage;

import java.util.Scanner;

public abstract class CommandHandler {
    private final String endCommand;

    protected CommandHandler(String endCommand) {
        this.endCommand = endCommand;
    }

    public void readAndHandleCommandsToEnd(Scanner scan) {
        boolean reachedEnd = this.handleCommand(scan.nextLine());

        while (!reachedEnd) {
            reachedEnd = this.handleCommand(scan.nextLine());
        }
    }

    private boolean handleCommand(String command) {
        if (command.equals(endCommand)) {
            return true;
        }

        processNonEndCommand(command);
        return false;
    }

    protected abstract void processNonEndCommand(String command);
}
