package classBox;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double length = scan.nextDouble();
        double width = scan.nextDouble();
        double height = scan.nextDouble();

        Box box;
        try {
            box = new Box(length, width, height);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.printf("Surface Area - %.2f%n", box.calculateSurfaceArea());
        System.out.printf("Lateral Surface Area - %.2f%n", box.calculateLateralSurfaceArea());
        System.out.printf("Volume - %.2f", box.calculateVolume());
    }
}