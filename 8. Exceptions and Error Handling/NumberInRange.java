import java.util.Optional;
import java.util.Scanner;

public class NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] rangeTokens = scanner.nextLine().split("\\s+");
        int lowerBound = Integer.parseInt(rangeTokens[0]);
        int upperBound = Integer.parseInt(rangeTokens[1]);

        System.out.printf("Range: [%d...%d]%n", lowerBound, upperBound);

        boolean isValidInput = false;
        while (!isValidInput) {
            String inputData = scanner.nextLine();

            Optional<Integer> number = Optional.empty();

            try {
                number = Optional.of(Integer.parseInt(inputData));
            } catch (NumberFormatException ignored) {}

            String output = "Invalid number: " + inputData;

            if (isInRange(number, lowerBound, upperBound)) {
                 output = "Valid number: " + inputData;
                isValidInput = true;
            }

            System.out.println(output);
        }
    }

    private static boolean isInRange(Optional<Integer> parsedNumber, int lowerBound, int upperBound) {
        return parsedNumber.isPresent() && parsedNumber.get() >= lowerBound && parsedNumber.get() <= upperBound;
    }
}
