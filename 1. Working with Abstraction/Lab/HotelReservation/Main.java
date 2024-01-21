package hotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String [] tokens = scan.nextLine().split(" ");
        double pricePerDay = Double.parseDouble(tokens[0]);
        int days = Integer.parseInt(tokens[1]);
        Season season = Season.valueOf(tokens[2]);
        Discount discount = Discount.valueOf(tokens[3]);

        double totalPrice = PriceCalculator.priceCalculator(pricePerDay, days, season, discount);
        System.out.printf("%.2f", totalPrice);
    }
}
