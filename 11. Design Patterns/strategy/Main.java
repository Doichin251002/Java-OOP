package strategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person("name1", "name1"));
        people.add(new Person("name2", "name2"));
        people.add(new Person("name3", "name3"));
        people.add(new Person("name4", "name4"));
        people.add(new Person("name5", "name5"));

        PeopleComparatorByFirstName byFirstName = new PeopleComparatorByFirstName();
        PeopleComparatorByLastName byLastName = new PeopleComparatorByLastName();

        people.sort(byFirstName);
        people.sort(byLastName.reversed());
    }
}
