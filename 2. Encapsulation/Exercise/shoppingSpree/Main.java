package shoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Person> people;
        List<Product> products;
        try {
            people = parsePeople(scan.nextLine());
            products = parseProduct(scan.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String command = scan.nextLine();
        while (!command.equals("END")) {
            String[] personAndProduct = command.split("\\s+");
            String currentPerson = personAndProduct[0];
            String currentProduct = personAndProduct[1];

            Person person = people.stream().filter(p -> p.getName().equals(currentPerson)).findFirst().get();
            Product product = products.stream().filter(p -> p.getName().equals(currentProduct)).findFirst().get();

            try {
                person.buyProduct(product);
                System.out.println(person.getName() + " bought " + product.getName());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }

            command = scan.nextLine();
        }

        for (Person person : people) {
            List<Product> personProducts = person.getProducts();
            System.out.print(person.getName() + " - ");
            if (personProducts.isEmpty()) {
                System.out.println("Nothing bought");
            } else {
                List<String> productNames = personProducts.stream()
                        .map(Product::getName)
                        .collect(Collectors.toList());

                System.out.println(String.join(", ", productNames));
            }
        }
    }

    private static List<Person> parsePeople(String inputLine) {
        List<Person> people = new ArrayList<>();

        String [] inputData = inputLine.split(";");
        for (String data : inputData) {
            String [] peopleArg = data.split("=");
            String name = peopleArg[0];
            double money = Double.parseDouble(peopleArg[1]);

            people.add(new Person(name, money));
        }

        return people;
    }

    private static List<Product> parseProduct(String inputLine) {
        List<Product> products = new ArrayList<>();

        String [] inputData = inputLine.split(";");
        for (String data : inputData) {
            String [] productArgs = data.split("=");
            String name = productArgs[0];
            double cost = Double.parseDouble(productArgs[1]);

            products.add(new Product(name, cost));
        }

        return products;
    }
}
