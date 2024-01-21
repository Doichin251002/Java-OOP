package StudentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor();

        String[] input = scanner.nextLine().split(" ");
        while (!input[0].equals("Exit")) {
            executor.executeCommand(input[0], input);

            input = scanner.nextLine().split(" ");
        }
    }
}
