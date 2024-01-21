package sortNameAge;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    private double salary;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(String firstName, String lastName, int age, double salary) {
        this(firstName, lastName, age);
        this.salary = salary;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    int getAge() {
        return age;
    }

    double getSalary() { return salary; }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.#");
        return String.format("this.firstName" + " " + this.lastName + " gets " + format.format(this.salary) + " leva.");
    }

    void increaseSalary(double bonus) {
        double actualBonus = bonus;
        if (this.age < 30) {
            actualBonus /= 2;
        }

        this.salary = salary * (1 + actualBonus / 100);
    }
}
