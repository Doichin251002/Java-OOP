package barracksWars.core.commands;

import barracksWars.annotations.Inject;
import barracksWars.interfaces.CommandInterpreter;
import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImp implements CommandInterpreter {
    private static final String COMMAND_PACKAGE_PATH = "barracksWars.core.commands.";
    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImp(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {
        Command command;
        try {
            Class commandClass = Class.forName( parseCommandToClassName(commandName));

            Constructor<Command> constructor = commandClass.getDeclaredConstructor(String[].class);

            command = constructor.newInstance(new Object[]{data});

            Field[] fields = commandClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    if (field.getType().equals(Repository.class)) {
                        field.setAccessible(true);

                        field.set(command, repository);

                    } else if (field.getType().equals(UnitFactory.class)) {
                        field.setAccessible(true);

                        field.set(command, unitFactory);
                    }
                }
            }

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return command;
    }

    private static String parseCommandToClassName(String commandName) {
        String firstLetterUppercase = commandName.substring(0, 1).toUpperCase();
        String restOfTheCommand = commandName.substring(1).toLowerCase();

        return COMMAND_PACKAGE_PATH + firstLetterUppercase + restOfTheCommand + "Command";
    }
}
