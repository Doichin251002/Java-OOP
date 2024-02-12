package singleton;

import java.util.ArrayList;
import java.util.List;

public class Datastore {
    private static Datastore instance;
    private List<Integer> database;

    private Datastore() {
        this.database = new ArrayList<>();
    }

    public static Datastore getInstance() {
        if (instance == null) {
            instance = new Datastore();
        }

        return instance;
    }

    public void createNumber(int num) {
        this.database.add(num);
    }

    public int readNumberAt(int index) {
        return this.database.get(index);
    }

    public void updateNumberAt(int index, int num) {
        this.database.set(index, num);
    }

    public int deleteNumberAt(int index) {
        return this.database.remove(index);
    }
}
