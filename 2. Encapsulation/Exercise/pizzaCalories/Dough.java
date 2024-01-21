package pizzaCalories;

import java.util.Arrays;

public class Dough {
    enum Modifier {
        White(1.5),
        Wholegrain(1.0),
        Crispy(0.9),
        Chewy(1.1),
        Homemade(1.0);

        private double modifier;
        Modifier(double modifier) {
            this.modifier = modifier;
        }

        public double getModifier() {
            return modifier;
        }
    }
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    private void setFlourType(String flourType) {
        if (!isExist(flourType)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!isExist(bakingTechnique)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public boolean isExist(String modifier) {
        return Arrays.stream(Modifier.values()).map(Enum::name).anyMatch(m -> m.equals(modifier));
    }

    public double calculateCalories() {
        return 2
                * weight
                * Modifier.valueOf(this.flourType).getModifier()
                * Modifier.valueOf(this.bakingTechnique).getModifier();
    }
}
