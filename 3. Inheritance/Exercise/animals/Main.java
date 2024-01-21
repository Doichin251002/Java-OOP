package animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        while (!command.equals("Beast!")) {
            String animalType = command;
            String [] animalTokens = scan.nextLine().split("\\s+");

            try {
            switch (animalType) {
                case "Cat":
                    Cat cat = new Cat(animalTokens[0], Integer.parseInt(animalTokens[1]), animalTokens[2]);
                    System.out.println(cat.toString());
                    break;
                case "Dog":
                    Dog dog = new Dog(animalTokens[0], Integer.parseInt(animalTokens[1]), animalTokens[2]);
                    System.out.println(dog.toString());
                    break;
                case "Frog":
                    Frog frog = new Frog(animalTokens[0], Integer.parseInt(animalTokens[1]), animalTokens[2]);
                    System.out.println(frog.toString());
                    break;
                case "Kitten":
                    Kitten kitten = new Kitten(animalTokens[0], Integer.parseInt(animalTokens[1]));
                    System.out.println(kitten.toString());
                    break;
                case "Tomcat":
                    Tomcat tomcat = new Tomcat(animalTokens[0], Integer.parseInt(animalTokens[1]));
                    System.out.println(tomcat.toString());
                    break;
                default: throw new IllegalArgumentException("Unknown animal type: " + animalType);
            }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input!");
            }
            command = scan.nextLine();
        }
    }
}
