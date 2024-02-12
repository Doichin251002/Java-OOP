package wildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;

    public Mammal(String name, Double weight, String livingRegion, AnimalType type) {
        super(name, weight, type);
        this.livingRegion = livingRegion;
    }

    protected String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.##");
        String formattedWeight = format.format(getWeight());
        return String.format("%s[%s, %s, %s, %d]"
                , getType(), getName(), formattedWeight, this.livingRegion, getFoodEaten());
    }
}
