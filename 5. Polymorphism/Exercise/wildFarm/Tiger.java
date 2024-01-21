package wildFarm;

public class Tiger extends Felime {
    private String livingRegion;
    public Tiger(String name, Double weight, String livingRegion) {
        super(name, weight, livingRegion, AnimalType.Tiger);
        this.livingRegion = livingRegion;
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    protected boolean willEatFood(Food food) {
        return food instanceof Meat;
    }
}
