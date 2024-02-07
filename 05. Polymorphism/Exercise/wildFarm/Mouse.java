package wildFarm;

public class Mouse extends Mammal{

    public Mouse(String name, Double weight, String livingRegion) {
        super(name, weight, livingRegion, AnimalType.Mouse);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    protected boolean willEatFood(Food food) {
        return food instanceof Vegetable;
    }
}
