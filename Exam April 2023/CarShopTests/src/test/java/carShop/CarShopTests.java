package carShop;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CarShopTests {
    private CarShop shop;
    private Car car;

    @Before
    public void initializeCarShop() {
        this.shop = new CarShop();
    }

    @Before
    public void initializeCar() {
        this.car = new Car("valid", 100, 5000);
    }

    @Test
    public void testValidConstructorWithEmptyListOfCars() {
        assertTrue(this.shop.getCars().isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testExceptionModifyingImmutableCollectionOfCars() {
        List<Car> cars = this.shop.getCars();

        cars.add(new Car("invalid", 0, 0));
    }

    @Test
    public void testGetValidCollectionOfCars() {
        String actualModel = this.car.getModel();
        int actualHorsePower = this.car.getHorsePower();

        this.shop.add(this.car);

        assertEquals(1, this.shop.getCars().size());
        assertEquals("valid", actualModel);
        assertEquals(100, actualHorsePower);
    }

    @Test
    public void testGetValidCountOfCars() {
        this.shop.add(new Car("valid1", 200, 10000));
        this.shop.add(new Car("valid2", 300, 15000));

        assertEquals(2, this.shop.getCount());
    }

    @Test
    public void testFindNonExistentCarWithMaxHorsePower() {
        this.shop.add(new Car("nonMax1", 200, 10000));
        this.shop.add(new Car("nonMax1", 400, 15000));
        int maxHorsePower = 500;

        List<Car> carWithMaxHorsePower = this.shop.findAllCarsWithMaxHorsePower(maxHorsePower);

        assertTrue(carWithMaxHorsePower.isEmpty());
    }

    @Test
    public void testFindValidCarWithMaxHorsePower() {
        this.shop.add(new Car("valid1", 200, 10000));
        this.shop.add(new Car("max", 400, 15000));
        int maxHorsePower = 300;

        List<Car> carWithMaxHorsePower = this.shop.findAllCarsWithMaxHorsePower(maxHorsePower);

        assertEquals(1, carWithMaxHorsePower.size());
        assertEquals("max", carWithMaxHorsePower.get(0).getModel());
        assertEquals(400, carWithMaxHorsePower.get(0).getHorsePower());
    }

    @Test
    public void testFindValidCarsWithMaxHorsePower() {
        this.shop.add(new Car("valid", 200, 10000));
        this.shop.add(new Car("max1", 300, 15000));
        this.shop.add(new Car("max2", 400, 20000));
        int maxHorsePower = 250;

        List<Car> carWithMaxHorsePower = this.shop.findAllCarsWithMaxHorsePower(maxHorsePower);

        assertEquals(2, carWithMaxHorsePower.size());
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionAddNullCar() {
        this.shop.add(null);
    }

    @Test
    public void testExceptionMessageForAddNullCar() {
        String actualMessage = "";

        try {
            this.shop.add(null);
        } catch (NullPointerException e) {
            actualMessage = e.getMessage();
        }

        assertEquals("Car cannot be null.", actualMessage);
    }

    @Test
    public void testAddValidCar() {
        String actualModel = this.car.getModel();
        int actualHorsePower = this.car.getHorsePower();

        this.shop.add(this.car);

        assertEquals(1, this.shop.getCount());
        assertEquals("valid", actualModel);
        assertEquals(100, actualHorsePower);
    }

    @Test
    public void testRemoveNonExistentCar() {
        boolean removed = this.shop.remove(this.car);

        assertFalse(removed);
    }

    @Test
    public void testRemoveValidCar() {
        this.shop.add(this.car);

        boolean removed = this.shop.remove(this.car);

        assertTrue(removed);
    }

    @Test
    public void testGetNonExistentLuxuryCarInEmptyCollectionOfCars() {
        Car mostLuxuryCar = this.shop.getTheMostLuxuryCar();

        assertNull(mostLuxuryCar);
    }

    @Test
    public void testGetValidLuxuryCar() {
        this.shop.add(new Car("car", 100, 8000));
        this.shop.add(new Car("luxury", 150, 20000));

        Car mostLuxuryCar = this.shop.getTheMostLuxuryCar();

        assertNotNull(mostLuxuryCar);
        assertEquals("luxury", mostLuxuryCar.getModel());
        assertEquals(150, mostLuxuryCar.getHorsePower());
    }

    @Test
    public void testFindNonExistentCarsByModel() {
        List<Car> carsByModel = this.shop.findAllCarByModel("invalid");

        assertTrue(carsByModel.isEmpty());
    }

    @Test
    public void testFindValidCarByModel() {
        String actualModel = this.car.getModel();
        int actualHorsePower = this.car.getHorsePower();
        this.shop.add(this.car);

        List<Car> carsByModel = this.shop.findAllCarByModel(actualModel);

        assertEquals(1, carsByModel.size());
        assertEquals("valid", actualModel);
        assertEquals(100, actualHorsePower);
    }

    @Test
    public void testFindValidCarsByModel() {
        this.shop.add(new Car("car", 100, 5500));
        this.shop.add(new Car("valid", 120, 7000));
        this.shop.add(new Car("valid", 150, 10000));

        List<Car> carsByModel = this.shop.findAllCarByModel("valid");

        assertEquals(2, carsByModel.size());
    }
}

