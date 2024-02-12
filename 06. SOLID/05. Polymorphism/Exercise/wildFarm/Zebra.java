package wildFarm;

public class Zebra extends Mammal {

    public Zebra(String name, Double weight, String livingRegion) {
        super(name, weight, livingRegion, AnimalType.Zebra);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    protected boolean willEatFood(Food food) {
        return food instanceof Vegetable;
    }
}
