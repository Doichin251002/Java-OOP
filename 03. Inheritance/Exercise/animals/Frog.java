package animals;

public class Frog extends Animal{
    public static final String FROG_SOUND = "Ribbit";

    public Frog(String name, int age, String gender) {
        super(name, age, gender);
        this.sound = FROG_SOUND;
    }
}
