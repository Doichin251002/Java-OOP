package footballTeamGenerator;

public class Validators {
    public static void validateName (String name) {
        if (name == null || name.trim().isEmpty() || name.equals(" ")) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
    }

    public static void validateStat (String statName, int statValue) {
        if (statValue < 0 || statValue > 100) {
            throw new IllegalArgumentException(statName + " should be between 0 and 100.");
        }
    }
}
