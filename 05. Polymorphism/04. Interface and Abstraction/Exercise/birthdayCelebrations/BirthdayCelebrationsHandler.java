package birthdayCelebrations;

import java.util.ArrayList;
import java.util.List;

public class BirthdayCelebrationsHandler extends CommandHandler {
    private List<Birthable> birthable;
    public BirthdayCelebrationsHandler(String endCommand, String splitPattern) {
        super(endCommand, splitPattern);
        this.birthable = new ArrayList<>();
    }

    @Override
    protected void processNonEndCommand(String[] parameters) {
        Birthable b = null;
        switch (parameters[0]) {
            case "Citizen":
                b = new Citizen(parameters[1], Integer.parseInt(parameters[2]), parameters[3], parameters[4]);
                break;
            case "Robot":
                new Robot(parameters[1], parameters[2]);
                break;
            case "Pet":
                b = new Pet(parameters[1], parameters[2]);
                break;
            default: throw new IllegalArgumentException("Unknown command!");
        }

        if (b != null) {
            birthable.add(b);
        }
    }

    public List<Birthable> getAllBirthable() {
        return birthable;
    }
}
