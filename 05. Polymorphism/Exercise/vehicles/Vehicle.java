package vehicles;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public Double drive(double distance) {
        double fuelRequired = distance * (fuelConsumption + getExtraFuelConsumption());
        if (fuelRequired > fuelQuantity) {
            return null;
        }

        fuelQuantity -= fuelRequired;
        return distance;
    }

    protected abstract double getExtraFuelConsumption();

    public void refuel(double liters) {
        if (liters <= 0) {
            throw new IllegalStateException("Fuel must be a positive number");
        }
        if (fuelQuantity + liters > tankCapacity) {
            throw new IllegalStateException("Cannot fit fuel in tank");
        }

        fuelQuantity += calculatedRefuelLitersAfterLoss(liters);
    }

    protected double calculatedRefuelLitersAfterLoss(double liters) {
        return liters;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }
}
