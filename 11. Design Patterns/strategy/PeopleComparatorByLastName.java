package strategy;

import java.util.Collections;
import java.util.Comparator;

public class PeopleComparatorByLastName implements Comparator<Person> {
    @Override
    public int compare(Person left, Person right) {
        return left.getLastName().compareTo(right.getLastName());
    }

    @Override
    public Comparator<Person> reversed() {
        return Collections.reverseOrder(this);
    }
}
