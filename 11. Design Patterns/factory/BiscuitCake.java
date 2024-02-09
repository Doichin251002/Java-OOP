package factory;

public class BiscuitCake extends Cake {
    private static final String PRODUCT = "Biscuit";

    public BiscuitCake() {}

    public BiscuitCake(double diameter, int pieces, double price) {
        super(PRODUCT, diameter, pieces, price);
    }
}
