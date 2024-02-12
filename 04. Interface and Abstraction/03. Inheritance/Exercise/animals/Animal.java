package animals;

public class Animal {
    private String name;
    private int age;
    private String gender;
    protected String sound;

    public Animal(String name, int age, String gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        validateNotBlank("Name", name);
        this.name = name;
    }

    private void validateNotBlank(String parameterName, String parameterValue) {
        if (parameterValue == null || parameterValue.trim().isEmpty()) {
            throw new IllegalArgumentException(parameterName + "should not be null or empty: " + parameterValue);
        }
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age should not be negative: " + age);
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    private void setGender(String gender) {
        validateNotBlank("Gender", gender);
        this.gender = gender;
    }

    public String produceSound() {
        return sound;
    }

    @Override
    public String toString() {
        return String.format("%s %n" +
                "%s %d %s %n" +
                "%s", this.getClass().getSimpleName(), this.getName(), this.getAge(), this.getGender(), this.produceSound());
    }
}
