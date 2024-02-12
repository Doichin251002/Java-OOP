package goldDigger.models.museum;

import java.util.*;

public class BaseMuseum implements Museum {
    private Set<String> exhibits;

    public BaseMuseum() {
        this.exhibits = new LinkedHashSet<>();
    }

    @Override
    public Collection<String> getExhibits() {
        return this.exhibits;
    }
}
