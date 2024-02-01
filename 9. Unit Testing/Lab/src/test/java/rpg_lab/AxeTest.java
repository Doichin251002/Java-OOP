package rpg_lab;

import org.junit.Before;
import org.junit.Test;
import rpg_lab.entities.Axe;
import rpg_lab.entities.Dummy;

import static org.junit.Assert.*;

public class AxeTest {
    private static final int ATTACK = 10;
    private static final int DURABILITY = 50;
    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 20;

    private Axe axe;
    private Axe brokenAxe;
    private Dummy target;

    @Before
    public void setUp() {
        this.axe = new Axe(ATTACK, DURABILITY);
        this.brokenAxe = new Axe(ATTACK, 0);

        this.target = new Dummy(HEALTH, EXPERIENCE);
    }

    @Test
    public void testAttackDecreasesDurability() {
        axe.attack(target);

        int expectedDurability = DURABILITY - 1;

        assertEquals(expectedDurability, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackThrowsAtBrokenAxe() {

        brokenAxe.attack(target);
    }
}
