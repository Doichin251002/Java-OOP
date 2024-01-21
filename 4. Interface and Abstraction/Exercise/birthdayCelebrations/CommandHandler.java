package birthdayCelebrations;

import java.util.Scanner;

public abstract class CommandHandler {
    private final String endCommand;
    private final String splitPattern;

    protected CommandHandler(String endCommand, String splitPattern) {
        this.endCommand = endCommand;
        this.splitPattern = splitPattern;
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

        processNonEndCommand(command.split(splitPattern));
        return false;
    }

    protected abstract void processNonEndCommand(String[] split);
}
