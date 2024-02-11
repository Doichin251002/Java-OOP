package magicGame.models.magicians;

import magicGame.models.magics.Magic;

import static magicGame.common.ExceptionMessages.*;

public abstract class MagicianImpl implements Magician{
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    public MagicianImpl(String username, int health, int protection, Magic magic) {
        setUsername(username);
        setHealth(health);
        setProtection(protection);
        setMagic(magic);

        this.isAlive = isAlive();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    private void setUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    @Override
    public int getProtection() {
        return this.protection;
    }

    private void setProtection(int protection) {
        if (protection < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    @Override
    public Magic getMagic() {
        return this.magic;
    }

    private void setMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        this.magic = magic;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void takeDamage(int points) {
        if (this.protection < points) {
            int damageTaken = points - protection;
            this.health -= damageTaken;
            this.protection = 0;

            this.isAlive = isAlive();
        } else {
            this.protection -= points;
        }
    }

    @Override
    public String toString() {
        return String.format(this.getClass().getSimpleName() + ": " + getUsername() + System.lineSeparator() +
                "Health: " + getHealth() + System.lineSeparator() +
                "Protection: " + getProtection() + System.lineSeparator() +
                "Magic: " + getMagic().getName());
    }
}
