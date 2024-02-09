package factory;

import java.util.Map;

public class CakeFactory {
    private Map<String, Cake> cakesWithProduct;

    public CakeFactory() {
        this.cakesWithProduct = setCakes();
    }

    private Map<String, Cake> setCakes() {
        return Map.of(
                "Choco", new ChocoCake(),
                "Fruit", new FruitCake(),
                "White", new WhiteCake(),
                "Biscuit", new BiscuitCake()
        );
    }

    public Cake createCake(String type, double diameter, int pieces, double price) {
        Cake cake = this.cakesWithProduct.get(type);

        cake.setDiameter(diameter);
        cake.setPieces(pieces);
        cake.setPrice(price);

        return cake;
    }
}
