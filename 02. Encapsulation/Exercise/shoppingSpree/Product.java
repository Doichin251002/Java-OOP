package shoppingSpree;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    private void setName(String name) {
        Validations.validateName(name);
        this.name = name;
    }

    private void setCost(double cost) {
        Validations.validateMoney(cost);

        this.cost = cost;
    }
}
