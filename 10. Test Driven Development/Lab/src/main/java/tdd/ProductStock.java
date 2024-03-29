package tdd;

public interface ProductStock extends Iterable<Product> {
    //getter
    int getCount();

    //Validations
    boolean contains(Product product);

    //Modifications
    void add(Product product);
    void changeQuantity(String label, int quantity) throws IllegalArgumentException;

    //Retrievals
    Product find(int index) throws IndexOutOfBoundsException;
    Product findByLabel(String label) throws IllegalArgumentException;
    Iterable<Product> findFirstByAlphabeticalOrder(int count);

    //Querying
    Iterable<Product> findAllInRange(double lo, double hi);
    Iterable<Product> findAllByPrice(double price);
    Iterable<Product> findFirstMostExpensiveProducts(int count);
    Iterable<Product> findAllByQuantity(int quantity);
}
