package animals;

public class Dog extends Animal{
    public static final String DOG_SOUND = "Woof!";

    public Dog(String name, int age, String gender) {
        super(name, age, gender);
        this.sound = DOG_SOUND;
    }
}
