package StudentSystem;

public class CommandExecutor {
    private StudentSystem studentSystem;

    public CommandExecutor() {
        this.studentSystem = new StudentSystem();
    }

    public void executeCommand(String command, String[] data) {
        String name = data[1];
        switch (command) {
            case "Create":
                int age = Integer.parseInt(data[2]);
                double grade = Double.parseDouble(data[3]);
                studentSystem.createStudent(name, age, grade);
                break;
            case "Show":
                String result = studentSystem.showStudent(name);
                if (result != null) {
                    System.out.println(result);
                }
                break;
        }
    }
}
