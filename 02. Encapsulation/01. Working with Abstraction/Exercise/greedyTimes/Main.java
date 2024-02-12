package greedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long capacityBag = Long.parseLong(scanner.nextLine());
        String[] safe = scanner.nextLine().split("\\s+");

        Map<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<>();
        long gold = 0;
        long gems = 0;
        long cash = 0;

        for (int i = 0; i < safe.length; i += 2) {
            String currentItem = safe[i];
            long currentQuantity = Long.parseLong(safe[i + 1]);

            String typeItem = "";

            if (currentItem.length() == 3) {
                typeItem = "Cash";
            } else if (currentItem.toLowerCase().endsWith("gem")) {
                typeItem = "Gem";
            } else if (currentItem.toLowerCase().equals("gold")) {
                typeItem = "Gold";
            }

            if (typeItem.equals("")) {
                continue;
            } else if (capacityBag < bag.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + currentQuantity) {
                continue;
            }

            switch (typeItem) {
                case "Gem":
                    if (addItem(bag, currentQuantity, typeItem, "Gold")) continue;
                    break;
                case "Cash":
                    if (addItem(bag, currentQuantity, typeItem, "Gem")) continue;
                    break;
            }

            if (!bag.containsKey(typeItem)) {
                bag.put((typeItem), new LinkedHashMap<>());
            }

            if (!bag.get(typeItem).containsKey(currentItem)) {
                bag.get(typeItem).put(currentItem, 0L);
            }


            bag.get(typeItem).put(currentItem, bag.get(typeItem).get(currentItem) + currentQuantity);
            switch (typeItem) {
                case "Gold":
                    gold += currentQuantity;
                    break;
                case "Gem":
                    gems += currentQuantity;
                    break;
                case "Cash":
                    cash += currentQuantity;
                    break;
            }
        }

        for (var entry : bag.entrySet()) {
            Long sumValues = entry.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.printf("<%s> $%s%n", entry.getKey(), sumValues);

            entry.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }

    private static boolean addItem(Map<String, LinkedHashMap<String, Long>> bag, long currentQuantity, String typeItem, String gem) {
        if (!bag.containsKey(typeItem)) {
            if (bag.containsKey(gem)) {
                return currentQuantity > bag.get("Gold").values().stream().mapToLong(e -> e).sum();
            } else {
                return true;
            }
        } else
            return bag.get(typeItem).values().stream().mapToLong(e -> e).sum() + currentQuantity > bag.get(gem).values().stream().mapToLong(e -> e).sum();
    }
}