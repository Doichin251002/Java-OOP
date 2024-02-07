package shoppingSpree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        Validations.validateName(name);

        this.name = name;
    }
    private void setMoney(double money) {
        Validations.validateMoney(money);

        this.money = money;
    }

    public void buyProduct(Product product) {
        double productCost = product.getCost();

        if (productCost > this.money) {
            throw new IllegalStateException(this.getName() + " can't afford " + product.getName());
        }

        this.money -= productCost;
        this.products.add(product);
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(this.products);
    }
}
