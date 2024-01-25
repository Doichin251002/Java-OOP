package solid.products.drinks;

import solid.products.Product;

public abstract class Drink implements Product {
    private double milliliters;
    private double caloriesPer100Grams;
    private double density;

    public Drink(double milliliters, double caloriesPer100Grams, double density) {
        this.milliliters = milliliters;
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.density = density;
    }

    public double getLitters() {
        return this.milliliters * 1000;
    }

    public double getWeight() {
        return this.milliliters * this.density;
    }

    public double getCalories() {
        return this.caloriesPer100Grams / 100 * getWeight();
    }
}
