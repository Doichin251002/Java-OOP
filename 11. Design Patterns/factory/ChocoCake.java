package factory;

public class ChocoCake extends Cake {
    private static final String PRODUCT = "Chocolate";

    public ChocoCake() {}
    public ChocoCake(double diameter, int pieces, double price) {
        super(PRODUCT, diameter, pieces, price);
    }
}
