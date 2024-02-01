package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AxeTest {
    private static final int ATTACK = 10;
    private static final int DURABILITY = 50;
    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 20;

    private Axe axe;
    private Axe brokenAxe;
    private Dummy dummy;

    @Before
    public void setUp() {
        this.axe = new Axe(ATTACK, DURABILITY);
        this.brokenAxe = new Axe(ATTACK, 0);

        this.dummy = new Dummy(HEALTH, EXPERIENCE);
    }

    @Test
    public void testAttackDecreasesDurability() {
        axe.attack(dummy);

        int expectedDurability = DURABILITY - 1;

        assertEquals(expectedDurability, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackThrowsAtBrokenAxe() {

        brokenAxe.attack(dummy);
    }
}
