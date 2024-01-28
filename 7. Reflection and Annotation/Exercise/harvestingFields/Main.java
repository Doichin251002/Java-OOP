package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class<RichSoilLand> clazz = RichSoilLand.class;

        String command = scanner.nextLine();

        while (!command.equals("HARVEST")) {
            printFieldsWithModifier(command, clazz);

            command = scanner.nextLine();
        }
    }

    private static void printFieldsWithModifier(String inputModifier, Class<?> clazz) {
		Stream<Field> fieldStream = Arrays.stream(clazz.getDeclaredFields());

		if (!inputModifier.equals("all")) {
			fieldStream = fieldStream.filter(f -> Modifier.toString(f.getModifiers()).equals(inputModifier));
		}

		fieldStream.forEach(Main::printField);
    }

    private static void printField(Field field) {
        String modifier = Modifier.toString(field.getModifiers());

        String typeName = field.getType().getTypeName();
        String[] typeTokens = typeName.split("\\.");
        String returnType = typeTokens[typeTokens.length - 1];

        String name = field.getName();

        System.out.printf("%s %s %s%n", modifier, returnType, name);
    }
}
