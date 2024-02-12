package factory;

public class WhiteCake extends Cake {
    private static final String PRODUCT = "Cream";

    public WhiteCake() {}

    public WhiteCake(double diameter, int pieces, double price) {
        super(PRODUCT, diameter, pieces, price);
    }
}
