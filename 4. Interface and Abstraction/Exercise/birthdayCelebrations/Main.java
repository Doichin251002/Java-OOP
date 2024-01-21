package birthdayCelebrations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BirthdayCelebrationsHandler handler = new BirthdayCelebrationsHandler("End", "\\s+");

        handler.readAndHandleCommandsToEnd(scan);

        String birthYear = scan.nextLine();

        for (Birthable b : handler.getAllBirthable()) {
            if (b.getBirthDate().endsWith(birthYear)) {
                System.out.println(b.getBirthDate());
            }
        }
    }
}
