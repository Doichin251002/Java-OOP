package p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {
    private static final String[] ELEMENTS = {"A", "B", "C"};
    private ListIterator listIterator;
    private ListIterator emptyListIterator;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.listIterator = new ListIterator(ELEMENTS);

        this.emptyListIterator = new ListIterator();
    }

    @Test
    public void testConstructorWithValidElements() {
        int elementsLength = ELEMENTS.length;
        String [] actualArray = new String[elementsLength];

        for (int i = 0; i < elementsLength; i++) {
            actualArray[i] = this.listIterator.print();

            this.listIterator.move();
        }

        assertArrayEquals(ELEMENTS, actualArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsAtNullElement() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testHasNext() {
        assertTrue(this.listIterator.hasNext());
        this.listIterator.move();

        assertTrue(this.listIterator.hasNext());
        this.listIterator.move();

        assertFalse(this.listIterator.hasNext());
    }

    @Test
    public void testMove() {
        assertTrue(this.listIterator.move());
        assertTrue(this.listIterator.move());

        assertFalse(this.listIterator.move());
    }

    @Test
    public void testPrintReturnValidElement() {
        assertEquals(ELEMENTS[0], this.listIterator.print());

        this.listIterator.move();

        assertEquals(ELEMENTS[1], this.listIterator.print());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintAtEmptyList() {
        this.emptyListIterator.print();
    }
}
