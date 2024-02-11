package computers;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTests {
    private ComputerManager manager;
    private Computer computer;
    @Before
    public void initializeComputers() {
        this.manager = new ComputerManager();
        this.computer = new Computer("HP", "15s", 1000);
    }

    @Test
    public void testConstructorWithEmptyComputers() {
        assertTrue(this.manager.getComputers().isEmpty());
    }

    @Test
    public void testGetComputers() {
        this.manager.addComputer(new Computer("comp1", "model1", 500));
        this.manager.addComputer(new Computer("comp2", "model2", 1000));

        assertEquals(2, this.manager.getComputers().size());
    }

    @Test
    public void testGetCount() {
        this.manager.addComputer(this.computer);

        assertEquals(1, this.manager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAddNullComputer() {
        this.manager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAddExistentComputer() {
        this.manager.addComputer(this.computer);
        this.manager.addComputer(this.computer);
    }

    @Test
    public void testAddValidComputer() {
        Computer comp1 = new Computer("comp1", "model1", 1000);

        this.manager.addComputer(comp1);

        assertEquals(1, this.manager.getCount());
        assertEquals("comp1", comp1.getManufacturer());
        assertEquals("model1", comp1.getModel());
    }

    @Test
    public void testAddValidComputersWithDifferentManufacturer() {
        this.manager.addComputer(new Computer("comp1", "model", 1000));
        this.manager.addComputer(new Computer("comp2", "model", 1500));

        assertEquals(2, this.manager.getCount());
    }

    @Test
    public void testAddValidComputersWithDifferentModels() {
        Computer comp1 = new Computer("comp", "model1", 1000);
        Computer comp2 = new Computer("comp", "model2", 1500);

        this.manager.addComputer(comp1);
        this.manager.addComputer(comp2);

        assertEquals(2, this.manager.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionRemoveNonExistentComputer() {
        this.manager.removeComputer("comp1", "model1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionRemoveNullComputer() {
        this.manager.removeComputer(null, null);
    }

    @Test
    public void testRemoveValidComputer() {
        this.manager.addComputer(this.computer);
        String expectedManufacturer = this.computer.getManufacturer();
        String expectedModel = this.computer.getModel();

        Computer removedComputer = this.manager.removeComputer(expectedManufacturer, expectedModel);

        assertEquals(expectedManufacturer, removedComputer.getManufacturer());
        assertEquals(expectedModel, removedComputer.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionGetComputerWithNullManufacturerAndModel() {
        this.manager.getComputer(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionGetNonExistentComputer() {
        this.manager.getComputer("invalid1", "invalid2");
    }

    @Test
    public void testGetValidComputer() {
        this.manager.addComputer(this.computer);

        Computer actualComputer = this.manager.getComputer("HP", "15s");

        assertEquals(this.computer.getManufacturer(), actualComputer.getManufacturer());
        assertEquals(this.computer.getModel(), actualComputer.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionGetComputersByNullManufacturer() {
        this.manager.addComputer(this.computer);

        this.manager.getComputersByManufacturer(null);
    }

    @Test
    public void testGetComputersByValidManufacturer() {
        String manufacturer = "manufacturer";
        this.manager.addComputer(new Computer("manufacturer", "model1", 1000));
        this.manager.addComputer(new Computer("manufacturer", "model2", 1500));
        this.manager.addComputer(new Computer("different", "model3", 1500));

        List<Computer> computersByManufacturer = this.manager.getComputersByManufacturer(manufacturer);

        assertEquals(2, computersByManufacturer.size());
        assertEquals(computersByManufacturer.get(0).getManufacturer(), computersByManufacturer.get(1).getManufacturer());
    }
}