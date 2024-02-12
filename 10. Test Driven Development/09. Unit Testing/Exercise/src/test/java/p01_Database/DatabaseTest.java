package p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {
    private static final Integer[] NUMBERS = {9, 13, 20, 1, 5, 32};
    public static final int CAPACITY = 16;
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(NUMBERS);
    }

    @Test
    public void testConstructorWithValidElements() {
        Integer[] actualElements = database.getElements();

        assertArrayEquals(NUMBERS, actualElements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWithoutElements() throws OperationNotSupportedException {
        Integer[] emptyArray = new Integer[0];

        new Database(emptyArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWithMoreThan16Elements() throws OperationNotSupportedException {
        Integer[] bigArray = new Integer[CAPACITY + 1];

        new Database(bigArray);
    }

    @Test
    public void testAddValidElement() throws OperationNotSupportedException {
        this.database.add(3);

        int dbSize = database.getElements().length;
        Integer actualElement = database.getElements()[dbSize - 1];

        assertEquals(Integer.valueOf(3), actualElement);
        assertEquals(NUMBERS.length + 1, dbSize);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsAtNullElement() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void testRemoveValidElement() throws OperationNotSupportedException {
        Integer[] elementsBeforeRemoving = this.database.getElements();
        Integer beforeLastElementBeforeRemoving = elementsBeforeRemoving[elementsBeforeRemoving.length - 2];

        this.database.remove();

        Integer[] elementsAfterRemoving = this.database.getElements();
        Integer lastElementAfterRemoving = elementsAfterRemoving[elementsAfterRemoving.length - 1];

        assertEquals(elementsBeforeRemoving.length - 1, elementsAfterRemoving.length);
        assertEquals(beforeLastElementBeforeRemoving, lastElementAfterRemoving);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsAtEmptyDatabase() throws OperationNotSupportedException {
        Database database = new Database(7);

        database.remove();
        database.remove();
    }
}
