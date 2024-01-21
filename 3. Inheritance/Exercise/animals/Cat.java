package animals;

public class Cat extends Animal{
    public static final String CAT_SOUND = "Meow meow";
    public Cat(String name, int age, String gender) {
        super(name, age, gender);
        this.sound = CAT_SOUND;
    }
}
