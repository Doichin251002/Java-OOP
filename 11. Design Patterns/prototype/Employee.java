package prototype;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private String town;

    public Employee(int id, String name, String department, double salary, String town) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.town = town;
    }

    public Employee clone() {
        return new Employee(id, name, department, salary, town);
    }
}
