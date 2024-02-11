package archeologicalExcavations;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExcavationTests {

    @Test(expected = NullPointerException.class)
    public void testConstructorExceptionWithNullName() {
        new Excavation(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorExceptionWithEmptyName() {
        new Excavation("     ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionWithNegativeCapacity() {
        new Excavation("Valid", -10);
    }

    @Test
    public void testConstructorWithZeroCapacity() {
        new Excavation("Valid", 0);
    }

    @Test
    public void testConstructorWithValidNameCapacity() {
        new Excavation("Valid", 10);
    }

    @Test
    public void testGetName() {
        Excavation excavation = new Excavation("Valid", 10);

        assertEquals("Valid", excavation.getName());
    }

    @Test
    public void testGetCapacity() {
        Excavation excavation = new Excavation("Valid", 10);

        assertEquals(10, excavation.getCapacity());
    }

    @Test
    public void testGetCountIsZeroOnEmptyExcavation() {
        Excavation excavation = new Excavation("Valid", 10);

        assertEquals(0, excavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddArcheologistsWithSameName() {
        Excavation excavation = new Excavation("Valid", 2);
        Archaeologist archaeologist = new Archaeologist("Name", 10);

        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddArcheologistsWithFullCapacity() {
        Excavation excavation = new Excavation("Valid", 2);
        Archaeologist archaeologist1 = new Archaeologist("Name1", 10);
        Archaeologist archaeologist2 = new Archaeologist("Name2", 10);
        Archaeologist archaeologist3 = new Archaeologist("Name3", 10);

        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);
        excavation.addArchaeologist(archaeologist3);
    }

    @Test
    public void testAddArcheologistsIncreasesCount() {
        Excavation excavation = new Excavation("Valid", 10);
        Archaeologist archaeologist1 = new Archaeologist("Name1", 10);
        Archaeologist archaeologist2 = new Archaeologist("Name2", 10);
        Archaeologist archaeologist3 = new Archaeologist("Name3", 10);

        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);
        excavation.addArchaeologist(archaeologist3);

        assertEquals(3, excavation.getCount());
    }

    @Test
    public void testRemoveArcheologistReturnsFalseForMissingName() {
        Excavation excavation = new Excavation("Valid", 2);
        Archaeologist archaeologist = new Archaeologist("Name", 10);
        excavation.addArchaeologist(archaeologist);

        boolean result = excavation.removeArchaeologist("name");

        assertFalse(result);
        assertEquals(1, excavation.getCount());
    }

    @Test
    public void testRemoveArcheologistReturnsTrueForMatchingName() {
        Excavation excavation = new Excavation("Valid", 2);
        Archaeologist archaeologist1 = new Archaeologist("Name1", 10);
        Archaeologist archaeologist2 = new Archaeologist("Name2", 10);
        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);

        boolean result = excavation.removeArchaeologist("Name1");

        assertTrue(result);
        assertEquals(1, excavation.getCount());
    }
}
