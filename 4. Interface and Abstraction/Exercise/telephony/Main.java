package telephony;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String [] numbers = scan.nextLine().split("\\s+");
        String [] urls = scan.nextLine().split("\\s+");

        Smartphone phone = new Smartphone(Arrays.asList(numbers), Arrays.asList(urls));

        System.out.print(phone.call());
        System.out.print(phone.browse());
    }
}
