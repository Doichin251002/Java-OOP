package tdd;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {
    private static final double PRODUCT_PRICE = 15.00;
    private static final int PRODUCT_QUANTITY = 9;
    private static final String PRODUCT_LABEL = "testLabel";

    private ProductStock instockWithProducts;
    private ProductStock emptyInStock;
    private Product product;

    @Before
    public void setUp() {
        this.product = new Product(PRODUCT_LABEL, PRODUCT_PRICE, PRODUCT_QUANTITY);

        this.instockWithProducts = new Instock();
        this.instockWithProducts.add(this.product);

        this.emptyInStock = new Instock();
    }

    @Test
    public void testContainsNotExistProductReturnFalse() {
        assertFalse(this.emptyInStock.contains(this.product));
    }

    @Test
    public void testAddInStockValidProduct() {
        this.instockWithProducts.add(this.product);

        assertTrue(this.instockWithProducts.contains(this.product));
    }

    @Test
    public void testAddInStockProductWithSameLabelShouldNotChangeStockState() {
        this.instockWithProducts.add(this.product);
        this.instockWithProducts.add(this.product);

        assertEquals(1, this.instockWithProducts.getCount());
    }

    @Test
    public void testGetCountOfProductsInStock() {
        assertEquals(0, this.emptyInStock.getCount());

        this.instockWithProducts.add(this.product);
        assertEquals(1, this.instockWithProducts.getCount());

        this.instockWithProducts.add(new Product("newLabel", 10.00, 5));
        assertEquals(2, this.instockWithProducts.getCount());
    }

    @Test
    public void testFindInStockProductWithValidIndex() {
        List<Product> products = stockWithMultipleProducts();

        int index = 3;
        Product expectedProduct = products.get(index);

        Product actualProduct = this.instockWithProducts.find(index);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindInStockThrowsAtProductWithIndexOutOfBounds() {
        List<Product> products = stockWithMultipleProducts();
        this.instockWithProducts.find(products.size() + 1);
    }

    @Test
    public void testChangeQuantityOfValidProductInStock() {
        Product productForUpdating = new Product("newLabel", 15.00, 2);
        this.instockWithProducts.add(productForUpdating);

        int expectedQuantity = productForUpdating.getQuantity() + 10;
        this.instockWithProducts.changeQuantity(productForUpdating.getLabel(), expectedQuantity);

        assertEquals(expectedQuantity, productForUpdating.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityThrowsAtNotExistProduct() {
        this.instockWithProducts.changeQuantity("notExist", 4);
    }

    @Test
    public void testFindProductInStockByLabel() {
        stockWithMultipleProducts();

        Product actualProduct = this.instockWithProducts.findByLabel(PRODUCT_LABEL);

        assertEquals(PRODUCT_LABEL, actualProduct.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelThrowsAtProductWithNoxExistLabel() {
        this.instockWithProducts.findByLabel("notExist");
    }

    @Test
    public void testFindProductsByAlphabeticalOrderReturnCollectionInValidSize() {
        stockWithMultipleProducts();
        int expectedSize = 5;

        Iterable<Product> iterable = this.instockWithProducts.findFirstByAlphabeticalOrder(expectedSize);

        List<Product> actualProducts = iterableToList(iterable);
        assertEquals(expectedSize, actualProducts.size());
    }

    @Test
    public void testFindProductsByAlphabeticalOrderReturnCollectionOrderedByLabel() {
        List<Product> expectedProducts = stockWithMultipleProducts().stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());

        int expectedSize = expectedProducts.size();
        Iterable<Product> iterable = this.instockWithProducts.findFirstByAlphabeticalOrder(expectedSize);

        List<Product> actualProducts = iterableToList(iterable);
        assertEquals(expectedSize, actualProducts.size());

        for (int i = 0; i < expectedSize; i++) {
            Product expectedProduct = expectedProducts.get(i);
            Product actualProduct = actualProducts.get(i);

            assertEquals(expectedProduct, actualProduct);
        }
    }

    @Test
    public void testFindProductsByAlphabeticalOrderReturnEmptyCollectionWhenZeroSize() {
        stockWithMultipleProducts();

        Iterable<Product> iterable = this.instockWithProducts.findFirstByAlphabeticalOrder(0);

        List<Product> products = iterableToList(iterable);
        assertEquals(0, products.size());
    }

    @Test
    public void testFindProductsByAlphabeticalOrderReturnEmptyCollectionWhenNotEnoughProducts() {
        int size = stockWithMultipleProducts().size();

        Iterable<Product> iterable = this.instockWithProducts.findFirstByAlphabeticalOrder(size + 1);

        List<Product> products = iterableToList(iterable);
        assertEquals(0, products.size());
    }

    @Test
    public void testFindProductsByPriceRangeReturnCollectionWithValidRange() {
        int lowerBound = 5;
        int upperBound = 19;
        List<Product> expectedProducts = stockWithMultipleProducts().stream()
                .filter(p -> p.getPrice() > lowerBound && p.getPrice() <= upperBound)
                .collect(Collectors.toList());

        Iterable<Product> iterable = this.instockWithProducts.findAllInRange(lowerBound, upperBound);
        List<Product> actualProducts = iterableToList(iterable);

        assertEquals(expectedProducts.size(), actualProducts.size());

        boolean hasNotOutOfRangePrices = actualProducts.stream()
                .map(Product::getPrice)
                .noneMatch(p -> p <= lowerBound || p > upperBound);

        assertTrue(hasNotOutOfRangePrices);
    }

    @Test
    public void testFindProductsByPriceRangeReturnCollectionInDescendingOrderByPrice() {
        int lowerBound = 5;
        int upperBound = 19;
        List<Product> expectedProducts = stockWithMultipleProducts().stream()
                .filter(p -> p.getPrice() > lowerBound && p.getPrice() <= upperBound)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());

        Iterable<Product> iterable = this.instockWithProducts.findAllInRange(lowerBound, upperBound);
        List<Product> actualProducts = iterableToList(iterable);

        assertEquals(expectedProducts.size(), actualProducts.size());

        for (int i = 0; i < expectedProducts.size(); i++) {
            Product expectedProduct = expectedProducts.get(i);
            Product actualProduct = actualProducts.get(i);

            assertEquals(expectedProduct, actualProduct);
        }
    }

    @Test
    public void testFindProductsByPriceRangeReturnEmptyCollectionWhenProductsPriceIsOutOfRange() {
        int lowerBound = 50;
        int upperBound = 100;
        stockWithMultipleProducts();

        Iterable<Product> iterable = this.instockWithProducts.findAllInRange(lowerBound, upperBound);

        List<Product> products = iterableToList(iterable);
        assertEquals(0, products.size());
    }

    @Test
    public void testFindProductsByExactPrice() {
        double priceForFinding = 8;
        stockWithMultipleProducts();

        Iterable<Product> iterable = this.instockWithProducts.findAllByPrice(priceForFinding);
        List<Product> actualProducts = iterableToList(iterable);

        for (Product p : actualProducts) {
            assertEquals(priceForFinding, p.getPrice(), 0.00);
        }
    }

    @Test
    public void testFindProductsByExactPriceReturnEmptyCollectionWhenNoMatches() {
        double price = 50;
        stockWithMultipleProducts();

        Iterable<Product> iterable = this.instockWithProducts.findAllByPrice(price);

        List<Product> actualProducts = iterableToList(iterable);
        assertEquals(0, actualProducts.size());
    }

    @Test
    public void testFindMostExpensiveProducts() {
        int expectedSize = 6;

        List<Product> expectedProducts = stockWithMultipleProducts().stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(expectedSize)
                .collect(Collectors.toList());

        Iterable<Product> iterable = this.instockWithProducts.findFirstMostExpensiveProducts(expectedSize);

        List<Product> actualProducts = iterableToList(iterable);
        assertEquals(expectedProducts.size(), actualProducts.size());

        for (int i = 0; i < expectedSize; i++) {
            Product expectedProduct = expectedProducts.get(i);
            Product actualProduct = actualProducts.get(i);

            assertEquals(expectedProduct, actualProduct);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMostExpensiveProductsThrowsAtNotEnoughProducts() {
        this.instockWithProducts.findFirstMostExpensiveProducts(this.instockWithProducts.getCount() + 1);
    }

    @Test
    public void testFindProductsByQuantity() {
        int expectedQuantity = 9;
        stockWithMultipleProducts();

        Iterable<Product> iterable = this.instockWithProducts.findAllByQuantity(expectedQuantity);
        List<Product> actualProducts = iterableToList(iterable);

        for (Product p : actualProducts) {
            assertEquals(expectedQuantity, p.getQuantity());
        }
    }

    @Test
    public void testFindProductsByQuantityReturnEmptyCollectionWhenNoMatches() {
        int quantity = 90;
        stockWithMultipleProducts();

        Iterable<Product> iterable = this.instockWithProducts.findAllByQuantity(quantity);

        List<Product> actualProducts = iterableToList(iterable);
        assertEquals(0, actualProducts.size());
    }

    @Test
    public void testIteratorProductsInStock() {
        List<Product> expectedProducts = stockWithMultipleProducts();

        Iterator<Product> iterator = this.instockWithProducts.iterator();

        List<Product> actualProducts = new ArrayList<>();
        iterator.forEachRemaining(actualProducts::add);
        assertEquals(expectedProducts, actualProducts);
    }

    private List<Product> stockWithMultipleProducts() {
        List<Product> products = List.of(
                this.product,
                new Product("testLabel4", 22, 9),
                new Product("testLabel3", 1, 9),
                new Product("testLabel8", 11, 7),
                new Product("testLabel2", 1, 9),
                new Product("testLabel7", 19, 13),
                new Product("testLabel5", 8, 5),
                new Product("testLabel6", 8, 9),
                new Product("testLabel9", 5, 9));

        products.forEach(this.instockWithProducts::add);

        return products;
    }

    private List<Product> iterableToList(Iterable<Product> iterable) {
        assertNotNull(iterable);

        List<Product> products = new ArrayList<>();

        iterable.forEach(products::add);

        return products;
    }
}