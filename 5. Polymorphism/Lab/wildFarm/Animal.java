package wildFarm;

public abstract class Animal {
    private String name;
    private AnimalType type;
    private Double weight;
    private Integer foodEaten;

    public Animal(String name, Double weight, AnimalType type) {
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.foodEaten = 0;
    }

    protected String getName() {
        return name;
    }

    protected AnimalType getType() {
        return type;
    }

    protected Double getWeight() {
        return weight;
    }

    protected Integer getFoodEaten() {
        return foodEaten;
    }

    public abstract void makeSound();
    protected abstract boolean willEatFood(Food food);
    public void eat(Food food) {
        if (!willEatFood(food)) {
            System.out.printf("%ss are not eating that type of food!\n", this.type.name());
            return;
        }

        this.foodEaten += food.getQuantity();
    }
}
