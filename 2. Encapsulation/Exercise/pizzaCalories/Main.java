package pizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Pizza pizza;
        Dough dough;
        try {
            pizza = parsePizza(scan.nextLine());
            dough = parseDough(scan.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        pizza.setDough(dough);

        String command = scan.nextLine();
        while (!command.equals("END")) {
            Topping topping;
            try {
                topping = parseTopping(command);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }

            pizza.addTopping(topping);

            command = scan.nextLine();
        }

        System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
    }

    private static Topping parseTopping(String inputLine) {
        String [] toppingTokens = inputLine.split("\\s+");

        String type = toppingTokens[1];
        double weight = Double.parseDouble(toppingTokens[2]);

        return new Topping(type, weight);
    }

    private static Dough parseDough(String inputLine) {
        String [] doughTokens = inputLine.split("\\s+");

        String type = doughTokens[1];
        String bakingTechnique = doughTokens[2];
        double weight = Double.parseDouble(doughTokens[3]);

        return new Dough(type, bakingTechnique, weight);
    }

    private static Pizza parsePizza(String inputLine) {
        String [] pizzaTokens = inputLine.split("\\s+");

        String name = pizzaTokens[1];
        int countToppings = Integer.parseInt(pizzaTokens[2]);

        return new Pizza(name, countToppings);
    }
}
