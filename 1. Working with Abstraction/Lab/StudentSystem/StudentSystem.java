package StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> repo;

    public StudentSystem() {
        this.repo = new HashMap<>();
    }

    public Map<String, Student> getRepo() {
        return this.repo;
    }

    public void createStudent(String name, int age, double grade) {
        if (repo.containsKey(name)) {
            return;
        }
        Student student = new Student(name, age, grade);
        repo.put(name, student);
    }

    public String showStudent(String name) {
        Student student = this.repo.get(name);

        if (student == null) {
            return null;
        }
        return student.toString();
    }
}
