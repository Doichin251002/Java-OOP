package solid.products.food;

import solid.products.Product;

public abstract class Food implements Product {
    private double grams;

    private double caloriesPer100Grams;

    public Food(double grams, double caloriesPer100Grams) {
        this.grams = grams;
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public double getGrams() {
        return this.grams;
    }

    public double getWeight() {
        return this.grams / 1000;
    }

    public double getCalories() {
        return caloriesPer100Grams / 100 * getWeight();
    }
}
