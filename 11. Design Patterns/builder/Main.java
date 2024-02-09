package builder;

public class Main {
    public static void main(String[] args) {
        Contact.builder()
                .withName("name")
                .withPhoneNumber("1234567")
                .withEmail("name@gmail.com")
                .build();
    }
}
