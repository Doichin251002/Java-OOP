package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.*;

public class SpotRepository implements Repository<Spot> {
    private List<Spot> spots;

    public SpotRepository() {
        this.spots = new ArrayList<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return this.spots;
    }

    @Override
    public void add(Spot entity) {
        this.spots.add(entity);
    }

    @Override
    public boolean remove(Spot entity) {
        return this.spots.removeIf(s -> s.getName().equals(entity.getName()));
    }

    @Override
    public Spot byName(String name) {
        return this.spots.stream().filter(s -> s.getName().equals(name)).findAny().orElse(null);
    }
}
