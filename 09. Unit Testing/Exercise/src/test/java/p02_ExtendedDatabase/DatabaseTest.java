package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {
    private static final Person[] PEOPLE = {new Person(1, "A"),
            new Person(2, "B"),
            new Person(3, "C"),
            new Person(4, "D")};
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(PEOPLE);
    }

    @Test
    public void testFindPersonByUsername() throws OperationNotSupportedException {
        Person expectedPerson = PEOPLE[0];

        Person actualPerson = this.database.findByUsername(expectedPerson.getUsername());

        assertEquals(expectedPerson.getId(), actualPerson.getId());
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindThrowsAtNullUsername() throws OperationNotSupportedException {
        this.database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsAtNotExistingElement() throws OperationNotSupportedException {
        this.database.findByUsername("Z");
    }

    @Test
    public void testFindPersonById() throws OperationNotSupportedException {
        Person expectedPerson = PEOPLE[0];

        Person actualPerson = this.database.findById(expectedPerson.getId());

        assertEquals(expectedPerson.getId(), actualPerson.getId());
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdThrowsAtNotExistingElement() throws OperationNotSupportedException {
        this.database.findById(100);
    }

}
