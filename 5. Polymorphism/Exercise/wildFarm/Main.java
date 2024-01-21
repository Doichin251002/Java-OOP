package wildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String command = scan.nextLine();
        while (!command.equals("End")) {
            String [] animalParams = command.split("\\s+");
            Animal animal = createAnimal(animalParams);
            animals.add(animal);

            String [] foodParams = scan.nextLine().split("\\s+");
            Food food = createFood(foodParams);

            animal.makeSound();
            animal.eat(food);

            command = scan.nextLine();
        }

        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private static Food createFood(String[] foodParams) {
        FoodType type = FoodType.valueOf(foodParams[0]);
        int quantity = Integer.parseInt(foodParams[1]);

        Food result = null;
        if (type == FoodType.Vegetable) {
            result = new Vegetable(quantity);
        } else if (type == FoodType.Meat) {
            result = new Meat(quantity);
        }

        return result;
    }

    private static Animal createAnimal(String[] animalParams) {
        AnimalType type = AnimalType.valueOf(animalParams[0]);

        Animal result = null;
        if (type == AnimalType.Cat) {
            result = new Cat(animalParams[1], Double.parseDouble(animalParams[2]), animalParams[3], animalParams[4]);
        } else if (type == AnimalType.Tiger) {
            result = new Tiger(animalParams[1], Double.parseDouble(animalParams[2]), animalParams[3]);
        } else if (type == AnimalType.Mouse) {
            result = new Mouse(animalParams[1], Double.parseDouble(animalParams[2]), animalParams[3]);
        } else if (type == AnimalType.Zebra) {
            result = new Zebra(animalParams[1], Double.parseDouble(animalParams[2]), animalParams[3]);
        }

        return result;
    }
}
