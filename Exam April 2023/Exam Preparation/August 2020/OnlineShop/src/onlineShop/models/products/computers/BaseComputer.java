package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        double avgComponentsPerformance = this.components.stream()
                .mapToDouble(Component::getOverallPerformance)
                .average()
                .orElse(0);

        return super.getOverallPerformance() + avgComponentsPerformance;
    }

    @Override
    public double getPrice() {
        return super.getPrice()
                + this.components.stream().mapToDouble(Component::getPrice).sum()
                + this.peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        boolean existingComponent = this.components.stream().anyMatch(c -> c.getClass() == component.getClass());

        if (existingComponent) {
            String componentType = component.getClass().getSimpleName();
            String computerType = this.getClass().getSimpleName();
            int computerId = this.getId();

            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT
                    , componentType, computerType, computerId));
        }

        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component removedComponent = this.components.stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);

        if (this.components.isEmpty() || removedComponent == null) {
            String computerType = this.getClass().getSimpleName();
            int computerId = this.getId();

            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT
                    , componentType, computerType, computerId));
        }

        this.components.remove(removedComponent);
        return removedComponent;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        boolean existingPeripheral = this.peripherals.stream().anyMatch(p -> p.getClass() == peripheral.getClass());

        if (existingPeripheral) {
            String peripheralType = peripheral.getClass().getSimpleName();
            String computerType = this.getClass().getSimpleName();
            int computerId = this.getId();

            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL
                    , peripheralType, computerType, computerId));
        }

        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral removedPeripheral = this.peripherals.stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheralType))
                .findFirst()
                .orElse(null);

        if (this.peripherals.isEmpty() || removedPeripheral == null) {
            String computerType = this.getClass().getSimpleName();
            int computerId = this.getId();

            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL
                    , peripheralType, computerType, computerId));
        }

        this.peripherals.remove(removedPeripheral);
        return removedPeripheral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(System.lineSeparator());
        sb.append(String.format(" " + COMPUTER_COMPONENTS_TO_STRING, getComponents().size())).append(System.lineSeparator());
        for (Component component : this.components) {
            sb.append("  ").append(component.toString()).append(System.lineSeparator());
        }

        double avgPeripheralsPerformance = this.peripherals.stream()
                .mapToDouble(Peripheral::getOverallPerformance)
                .average()
                .orElse(0);
        sb.append(String.format(" " + COMPUTER_PERIPHERALS_TO_STRING
                , getPeripherals().size(), avgPeripheralsPerformance)).append(System.lineSeparator());
        for (Peripheral peripheral : this.peripherals) {
            sb.append("  ").append(peripheral.toString()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
