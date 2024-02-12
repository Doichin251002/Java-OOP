package stackOfStrings;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackOfStrings {
    private ArrayList<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item) {
        this.data.add(item);
    }

    public String pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.data.remove(getIndex());
    }

    public String peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.data.get(getIndex());
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int getIndex() {
        return this.size() - 1;
    }

    public int size() {
        return this.data.size();
    }
}
