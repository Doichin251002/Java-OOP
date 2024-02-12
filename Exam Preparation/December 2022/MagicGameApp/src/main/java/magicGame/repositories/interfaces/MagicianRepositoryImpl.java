package magicGame.repositories.interfaces;

import magicGame.models.magicians.Magician;

import java.util.*;

import static magicGame.common.ExceptionMessages.INVALID_MAGICIAN_REPOSITORY;

public class MagicianRepositoryImpl implements MagicianRepository<Magician> {
    private Map<String, Magician> data = new LinkedHashMap<>();
    @Override
    public Collection<Magician> getData() {
        return Collections.unmodifiableCollection(this.data.values());
    }

    @Override
    public void addMagician(Magician magician) {
        if (magician == null) {
            throw new NullPointerException(INVALID_MAGICIAN_REPOSITORY);
        }

        this.data.put(magician.getUsername(), magician);
    }

    @Override
    public boolean removeMagician(Magician magician) {
        return this.data.remove(magician.getUsername(), magician);
    }

    @Override
    public Magician findByUsername(String name) {
        return this.data.get(name);
    }
}
