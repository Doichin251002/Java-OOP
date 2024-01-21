package foodShortage;

public class Rebel implements Person, Buyer {
    private int food;
    private String group;
    private String name;
    private int age;

    public Rebel(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.food = 0;
        this.group = group;
    }

    @Override
    public void buyFood() {
        this.food += 5;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }
}
