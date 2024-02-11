package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.*;

public class DiscovererRepository implements Repository<Discoverer> {
    private Set<Discoverer> discoverers;

    public DiscovererRepository() {
        this.discoverers = new LinkedHashSet<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(discoverers);
    }

    @Override
    public void add(Discoverer entity) {
        this.discoverers.add(entity);
    }

    @Override
    public boolean remove(Discoverer entity) {
        return this.discoverers.removeIf(d -> d.getName().equals(entity.getName()));
    }

    @Override
    public Discoverer byName(String name) {
        return this.discoverers.stream().filter(d -> d.getName().equals(name)).findAny().orElse(null);
    }
}
