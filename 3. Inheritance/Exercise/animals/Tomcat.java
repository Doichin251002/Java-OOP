package animals;

public class Tomcat extends Cat{
    public static final String TOMCAT_GENDER = "Male";
    public static final String TOMCAT_SOUND = "MEOW";

    public Tomcat(String name, int age) {
        super(name, age, TOMCAT_GENDER);
        this.sound = TOMCAT_SOUND;
    }
}
