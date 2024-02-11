package goldDigger.models.discoverer;

import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.DISCOVERER_ENERGY_LESS_THAN_ZERO;
import static goldDigger.common.ExceptionMessages.DISCOVERER_NAME_NULL_OR_EMPTY;

public abstract class BaseDiscoverer implements Discoverer {
    private String name;
    private double energy;
    private Museum museum;

    public BaseDiscoverer(String name, double energy) {
        setName(name);
        setEnergy(energy);
        this.museum = new BaseMuseum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(DISCOVERER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    private void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public Museum getMuseum() {
        return this.museum;
    }

    @Override
    public boolean canDig() {
        return this.energy > 0;
    }

    @Override
    public void dig() {
        this.energy -= 15;
        if (energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    public String toString() {
        String exhibits = String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, this.museum.getExhibits());

        if (this.museum.getExhibits().isEmpty()) {
            exhibits = "None";
        }

        return String.format(FINAL_DISCOVERER_NAME, this.name) + "\n" +
                String.format(FINAL_DISCOVERER_ENERGY, this.energy) + "\n" +
                String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, exhibits);
    }
}
