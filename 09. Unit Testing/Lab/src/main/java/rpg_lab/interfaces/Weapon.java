package rpg_lab.interfaces;

import rpg_lab.interfaces.Target;

public interface Weapon {
    int getAttackPoints();

    int getDurabilityPoints();

    void attack(Target target);

}
