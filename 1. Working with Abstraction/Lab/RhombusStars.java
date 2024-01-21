import java.util.Scanner;

public class RhombusStars {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = Integer.parseInt(scan.nextLine());

        for (int i = 1; i <= size; i++) {
            printRow(i, size - i);
        }
        for (int i = size - 1; i > 0; i--) {
            printRow(i, size - i);
        }
    }

    private static void printRow(int numberStars, int numberSpaces) {
        for (int i = 0; i < numberSpaces; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < numberStars; i++) {
            System.out.print("* ");
        }
        System.out.println();
    }
}