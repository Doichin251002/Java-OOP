package strategy;

import java.util.Collections;
import java.util.Comparator;

public class PeopleComparatorByFirstName implements Comparator<Person> {
    @Override
    public int compare(Person left, Person right) {
        return left.getFirstName().compareTo(right.getFirstName());
    }

    @Override
    public Comparator<Person> reversed() {
        return Collections.reverseOrder(this);
    }
}

