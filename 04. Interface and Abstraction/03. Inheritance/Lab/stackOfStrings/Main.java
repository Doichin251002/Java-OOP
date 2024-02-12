package stackOfStrings;

public class Main {
    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();

        stack.push("Dodi");
        stack.pop();
        stack.push("Item");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
