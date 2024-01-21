package vehicles;

public class Truck extends Vehicle {
    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    protected double getExtraFuelConsumption() {
        return 1.6;
    }

    @Override
    protected double calculatedRefuelLitersAfterLoss(double liters) {
        return super.calculatedRefuelLitersAfterLoss(liters) * 0.95;
    }
}
