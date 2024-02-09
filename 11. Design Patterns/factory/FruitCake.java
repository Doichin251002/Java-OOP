package factory;

public class FruitCake extends Cake {
    private static final String PRODUCT = "Fruit";

    public FruitCake() {}

    public FruitCake(double diameter, int pieces, double price) {
        super(PRODUCT, diameter, pieces, price);
    }
}
