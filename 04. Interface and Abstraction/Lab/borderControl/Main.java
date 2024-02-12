package borderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Identifiable> identifier = new ArrayList<>();

        String command = scan.nextLine();
        while (!command.equals("End")) {
            String [] data = command.split("\\s+");
            switch (data.length) {
                case 2:
                    identifier.add(new Robot(data[1], data[0]));
                    break;
                case 3:
                    identifier.add(new Citizen(data[0], Integer.parseInt(data[1]), data[2]));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid input");
            }
            command = scan.nextLine();
        }

        String badIdSuffix = scan.nextLine();
        identifier.stream()
                .map(Identifiable::getId)
                .filter(id -> id.endsWith(badIdSuffix))
                .forEach(System.out::println);
    }
}
