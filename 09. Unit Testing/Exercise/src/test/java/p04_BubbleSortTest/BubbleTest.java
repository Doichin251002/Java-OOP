package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {
    @Test
    public void testBubbleSort() {
        int[] numbers = {4, 2, 0, 8, 15, 31, 11, 7, 1, -12};
        int[] sortedArray = {-12, 0, 1, 2, 4, 7, 8, 11, 15, 31};

        Bubble.sort(numbers);

        Assert.assertArrayEquals(sortedArray, numbers);
    }
}
