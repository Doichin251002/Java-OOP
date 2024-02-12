package solid.calculators;

import solid.products.Product;

import java.util.List;

public class CalorieCalculator implements Calculator {
    public double sum(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getCalories)
                .sum();
    }

    public double average(List<Product> products) {
        return sum(products) / products.size();
    }
}
