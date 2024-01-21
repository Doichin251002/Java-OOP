package CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Card.Rank rank = Card.Rank.valueOf(scan.nextLine());
        Card.Suit suit = Card.Suit.valueOf(scan.nextLine());

        System.out.println(new Card(rank, suit));
    }
}