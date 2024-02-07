package solid;

import solid.calculators.Calculator;
import solid.products.Product;

import java.util.List;

public class ConsolePrinter {
    private static final String SUM_OUTPUT_FORMAT = "Sum: %.2f%n";
    private static final String AVERAGE_OUTPUT_FORMAT = "Average: %.2f%n";

    public void printSum(double sum) {
        System.out.printf((SUM_OUTPUT_FORMAT), sum);
    }

    public void printAverage(double average) {
        System.out.printf((AVERAGE_OUTPUT_FORMAT), average);
    }
}
