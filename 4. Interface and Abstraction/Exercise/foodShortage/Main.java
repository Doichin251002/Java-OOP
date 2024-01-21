package foodShortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Buyer> buyers = new HashMap<>();

        int count = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < count; i++) {
            String inputData = scan.nextLine();
            String [] personParameters = inputData.split("\\s+");
            switch (personParameters.length) {
                case 3:
                    Rebel rebel = new Rebel(personParameters[0], Integer.parseInt(personParameters[1]), personParameters[2]);
                    buyers.put(rebel.getName(), rebel);
                    break;
                case 4:
                    Citizen citizen = new Citizen(personParameters[0], Integer.parseInt(personParameters[1]), personParameters[2], personParameters[3]);
                    buyers.put(citizen.getName(), citizen);
                    break;
                default: throw new IllegalArgumentException("Unknown input: " + inputData);
            }
        }
        FoodShortageHandler handler = new FoodShortageHandler(buyers, "End");
        handler.readAndHandleCommandsToEnd(scan);

        System.out.println(buyers.values().stream().mapToInt(Buyer::getFood).sum());
    }
}
