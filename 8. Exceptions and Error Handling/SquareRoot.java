import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputData = scanner.nextLine();
        try {
            double sqrt = sqrt(inputData);

            System.out.printf("%.2f%n", sqrt);
        } catch (NumberFormatException | NegativeSQRTInputException e) {
            System.out.println("Invalid");
        } finally {
            System.out.println("Goodbye");
        }
    }

    private static double sqrt(String inputData) {
        double number = Double.parseDouble(inputData);

        if (number < 0) {
            throw new NegativeSQRTInputException("Number " + number + " is negative");
        }

        return Math.sqrt(number);
    }


}
