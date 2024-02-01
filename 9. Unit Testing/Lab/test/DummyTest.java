package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DummyTest {
    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 10;
    private static final int ATTACK_POINTS = 20;

    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setUp() {
        this.dummy = new Dummy(HEALTH, EXPERIENCE);
        this.deadDummy = new Dummy(0, EXPERIENCE);
    }

    @Test
    public void testDecreaseHealthInAttacked() {
        this.dummy.takeAttack(ATTACK_POINTS);

        int expectedHealth = HEALTH - ATTACK_POINTS;

        assertEquals(expectedHealth, this.dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTokenAttackThrowsAtDeadDummy() {
        this.deadDummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void testGiveExperienceAtDeadDummy() {
        int actualExperience = this.deadDummy.giveExperience();

        assertEquals(EXPERIENCE, actualExperience);
    }

    @Test(expected = IllegalStateException.class)
    public void testGivenExperienceThrowsAtAliveDummy() {
        this.dummy.giveExperience();
    }
}
