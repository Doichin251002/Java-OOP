package solid.calculators;

import solid.products.Product;

import java.util.List;

public class WeightCalculator implements Calculator{
    public double sum(List<Product> products) {
        return products.stream().mapToDouble(Product::getWeight).sum();
    }

    public double average(List<Product> products) {
        return sum(products) / products.size();
    }
}
