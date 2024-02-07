package randomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList<T> extends ArrayList<T> {
    private final Random random;
    public RandomArrayList() {
        super();
        this.random = new Random();
    }

    public T getRandomElement() {
        int index = random.nextInt(size());
        T element = this.get(index);
        this.remove(index);

        return element;
    }
}
