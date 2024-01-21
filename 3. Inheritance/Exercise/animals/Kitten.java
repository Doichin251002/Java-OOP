package animals;

public class Kitten extends Cat{
    public static final String KITTEN_GENDER = "Female";
    public static final String KITTEN_SOUND = "Meow";

    public Kitten(String name, int age) {
        super(name, age, KITTEN_GENDER);
        this.sound = KITTEN_SOUND;
    }
}
