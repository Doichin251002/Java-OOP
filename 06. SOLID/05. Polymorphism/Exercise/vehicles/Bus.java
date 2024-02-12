package vehicles;

public class Bus extends Vehicle {
    private boolean isEmpty;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    public void setUpEmptyDrive() {
        isEmpty = true;
    }

    @Override
    protected double getExtraFuelConsumption() {
        if (!isEmpty) {
            return 1.4;
        }
        return 0;
    }
}
