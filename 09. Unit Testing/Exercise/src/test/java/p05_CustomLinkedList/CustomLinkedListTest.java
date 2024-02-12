package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    private CustomLinkedList<String> list;

    @Before
    public void setUp() {
        this.list = new CustomLinkedList<>();

        this.list.add("A");
        this.list.add("B");
        this.list.add("C");
    }

    @Test
    public void testGetElementAtValidIndex() {
        String expectedElement = "C";
        int indexOfElement = this.list.indexOf(expectedElement);

        String actualElement = this.list.get(indexOfElement);

        assertEquals(expectedElement, actualElement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetElementThrowsAtNegativeIndex() {
        this.list.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetElementThrowsAtIndexBiggerThanSize() {
        this.list.get(this.list.getCount() + 1);
    }

    @Test
    public void testAddElement() {
        String newElement = "D";
        int sizeBeforeAdding = this.list.getCount();

        this.list.add(newElement);

        assertEquals(this.list.getCount() - 1, this.list.indexOf(newElement));
        assertEquals(sizeBeforeAdding + 1, this.list.getCount());
    }

    @Test
    public void testSetElementOfValidIndex() {
        String elementForSetting = "E";
        int indexForSetting = 2;

        this.list.set(indexForSetting, elementForSetting);

        assertEquals(elementForSetting, this.list.get(indexForSetting));
        assertEquals(indexForSetting, this.list.indexOf(elementForSetting));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetThrowsAtNegativeIndex() {
        this.list.set(-1, "E");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetThrowsAtIndexBiggerThanSize() {
        this.list.set(this.list.getCount() + 1, "E");
    }

    @Test
    public void testRemoveAtValidIndex() {
        String lastElementForRemoving = "C";
        int indexOfRemovingElement = this.list.indexOf(lastElementForRemoving);
        String previousElementBeforeRemoving = this.list.get(indexOfRemovingElement - 1);
        int listSizeBeforeRemoving = this.list.getCount();

        this.list.removeAt(indexOfRemovingElement);

        String lastElementAfterRemoving = this.list.get(this.list.getCount() - 1);

        assertEquals(2, indexOfRemovingElement);
        assertEquals(previousElementBeforeRemoving, lastElementAfterRemoving);
        assertEquals(listSizeBeforeRemoving - 1, this.list.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtThrowsAtNegativeIndex() {
        this.list.removeAt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtThrowsAtIndexBiggerThanSize() {
        this.list.removeAt(this.list.getCount() + 1);
    }

    @Test
    public void testRemoveExistElement() {
        String lastElementForRemoving = "C";
        int indexOfRemovingElement = this.list.indexOf(lastElementForRemoving);
        String previousElementBeforeRemoving = this.list.get(indexOfRemovingElement - 1);
        int listSizeBeforeRemoving = this.list.getCount();

        this.list.remove(lastElementForRemoving);

        String lastElementAfterRemoving = this.list.get(this.list.getCount() - 1);

        assertEquals(2, indexOfRemovingElement);
        assertEquals(previousElementBeforeRemoving, lastElementAfterRemoving);
        assertEquals(listSizeBeforeRemoving - 1, this.list.getCount());
    }

    @Test
    public void testRemoveNotExistElementReturnNegative1() {
        assertEquals(-1, this.list.remove("Z"));
    }

    @Test
    public void testIndexOfValidElement() {
        assertEquals(0, this.list.indexOf("A"));
    }

    @Test
    public void testIndexOfNotExistElementReturnNegative1() {
        assertEquals(-1, this.list.indexOf("Z"));
    }

    @Test
    public void testContainsValidElement() {
        assertTrue(this.list.contains("A"));
    }

    @Test
    public void testContainsNotExistElementReturnFalse() {
        assertFalse(this.list.contains("Z"));
    }
}
