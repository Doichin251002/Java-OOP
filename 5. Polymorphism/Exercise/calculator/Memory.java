package calculator;

import java.util.Stack;

public class Memory {
    Stack<Integer> stack;

    public Memory() {
        this.stack = new Stack<>();
    }

    public void save(int operand) {
        this.stack.push(operand);
    }

    public int recall() {
        return this.stack.pop();
    }
}
