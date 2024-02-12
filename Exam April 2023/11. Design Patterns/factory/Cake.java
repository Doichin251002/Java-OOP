package factory;

public abstract class Cake {
    private String product;
    private double diameter;
    private int pieces;
    private double price;

    public Cake() {}

    public Cake(String product, double diameter, int pieces, double price) {
        this.product = product;
        this.diameter = diameter;
        this.pieces = pieces;
        this.price = price;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
