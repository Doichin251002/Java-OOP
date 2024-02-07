package pizzaCalories;

import java.util.Arrays;

public class Topping {
    enum Ingredients {
        Meat(1.2),
        Veggies(0.8),
        Cheese(1.1),
        Sauce(0.9);

        private double modifier;
        Ingredients(double modifier) {
            this.modifier = modifier;
        }

        public double getModifier() {
            return modifier;
        }
    }
    private String toppingType;
    private double weight;

    Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        if (!isExist(toppingType)) {
            throw new IllegalArgumentException("Cannot place " + toppingType + "on top of your pizza.");
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(this.toppingType + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    private boolean isExist(String ingredient) {
        return Arrays.stream(Ingredients.values()).map(Enum::name).anyMatch(doughName -> doughName.equals(ingredient));
    }

    public double calculateCalories() {
        return 2
                * weight
                * Ingredients.valueOf(toppingType).getModifier();
    }
}
