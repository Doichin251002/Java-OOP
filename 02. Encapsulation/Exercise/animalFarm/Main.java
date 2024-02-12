package animalFarm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        int age = Integer.parseInt(scan.nextLine());

        Chicken chicken;
        try {
            chicken = new Chicken(name, age);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(chicken);
    }
}
