package HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Class<Reflection> reflectionClass = Reflection.class;

        Field[] classFields = reflectionClass.getDeclaredFields();
        Arrays.stream(classFields)
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.println(f.getName() + " must be private!"));

        Method[] classMethods = reflectionClass.getDeclaredMethods();

        Arrays.stream(classMethods)
                .filter(m -> m.getName().startsWith("get") && m.getParameterCount() == 0)
                .filter(m -> !Modifier.isPublic(m.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(f -> System.out.println(f.getName() + " have to be public!"));

        Arrays.stream(classMethods)
                .filter(m -> m.getName().startsWith("set") && m.getParameterCount() == 1)
                .filter(m -> !Modifier.isPrivate(m.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(f -> System.out.println(f.getName() + " have to be private!"));
    }
}