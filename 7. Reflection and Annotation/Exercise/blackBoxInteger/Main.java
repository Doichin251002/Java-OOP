package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class<BlackBoxInt> clazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();

        Field innerValue = clazz.getDeclaredField("innerValue");
        innerValue.setAccessible(true);

        String command = scanner.nextLine();

        while (!command.equals("END")) {
            String[] commandTokens = command.split("_");
            String methodName = commandTokens[0];
            int parameterValue = Integer.parseInt(commandTokens[1]);

            invokeMethod(clazz, blackBoxInt, methodName, parameterValue);

            System.out.println(innerValue.get(blackBoxInt));

            command = scanner.nextLine();
        }
    }

    private static void invokeMethod(Class<BlackBoxInt> clazz, BlackBoxInt blackBoxInt, String methodName, int parameterValue) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = clazz.getDeclaredMethod(methodName, int.class);

        method.setAccessible(true);

        method.invoke(blackBoxInt, parameterValue);
    }
}
