import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnterNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> validNumbers = new ArrayList<>();

        int lowerBound = 1;
        int upperBound = 100;

        while (validNumbers.size() < 10) {
            String inputData = scanner.nextLine();

            try {
                lowerBound = readNumber(inputData, lowerBound, upperBound);
                validNumbers.add(lowerBound);
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid Number!");
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(validNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    private static int readNumber(String inputData, int lowerBound, int upperBound) {
        int number = Integer.parseInt(inputData);

        if (number <= lowerBound || number >= upperBound) {
            throw new IllegalStateException(String.format("Your number is not in range %d - 100!", lowerBound));
        }

        return number;
    }
}
