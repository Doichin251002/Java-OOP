package magicGame.repositories.interfaces;

import magicGame.models.magics.Magic;

import java.util.*;

import static magicGame.common.ExceptionMessages.INVALID_MAGIC_REPOSITORY;

public class MagicRepositoryImpl implements MagicRepository<Magic>{
    private Map<String, Magic> data = new HashMap<>();

    @Override
    public Collection<Magic> getData() {
        return Collections.unmodifiableCollection(this.data.values());
    }

    @Override
    public void addMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(INVALID_MAGIC_REPOSITORY);
        }

        data.put(magic.getName(), magic);
    }

    @Override
    public boolean removeMagic(Magic magic) {
        return this.data.remove(magic.getName(), magic);
    }

    @Override
    public Magic findByName(String name) {
        return this.data.get(name);
    }
}
