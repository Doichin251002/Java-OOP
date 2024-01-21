package sayHelloExtended;
public class Bulgarian extends BasePerson implements Person {

    public Bulgarian(String name) {
        super(name);
    }
    @Override
    public String getName() {
        return getName();
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
