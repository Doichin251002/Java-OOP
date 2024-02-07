package wildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String name, Double weight, String livingRegion, String breed) {
        super(name, weight, livingRegion, AnimalType.Cat);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    protected boolean willEatFood(Food food) {
        return true;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.##");
        String formattedWeight = format.format(getWeight());
        return String.format("%s[%s, %s, %s, %s, %d]"
                , getType(), getName(), this.breed, formattedWeight, getLivingRegion(), getFoodEaten());
    }
}
