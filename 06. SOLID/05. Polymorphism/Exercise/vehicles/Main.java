package vehicles;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final String CAR_NAME = "Car";
    public static final String TRUCK_NAME = "Truck";
    public static final String BUS_NAME = "Bus";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DecimalFormat format = new DecimalFormat("###.##");

        String [] carParams = scan.nextLine().split("\\s+");
        String [] truckParams = scan.nextLine().split("\\s+");
        String [] busParams = scan.nextLine().split("\\s+");

        Map<String, Vehicle> vehiclesByName = new LinkedHashMap<>();
        vehiclesByName.put(CAR_NAME, new Car(Double.parseDouble(carParams[1]), Double.parseDouble(carParams[2]), Double.parseDouble(carParams[3])));
        vehiclesByName.put(TRUCK_NAME, new Truck(Double.parseDouble(truckParams[1]), Double.parseDouble(truckParams[2]), Double.parseDouble(truckParams[3])));
        vehiclesByName.put(BUS_NAME, new Bus(Double.parseDouble(busParams[1]), Double.parseDouble(busParams[2]), Double.parseDouble(busParams[3])));

        int numCommands = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < numCommands; i++) {
            String [] commandParts = scan.nextLine().split("\\s+");
            String operation = commandParts[0];
            String vehicleName = commandParts[1];

            Vehicle vehicle = vehiclesByName.get(vehicleName);

            switch (operation) {
                case "DriveEmpty":
                    Bus bus = (Bus) vehiclesByName.get(BUS_NAME);
                    bus.setUpEmptyDrive();
                case "Drive":
                    Double travelledKm = vehicle.drive(Double.parseDouble(commandParts[2]));
                    if (travelledKm != null) {
                        System.out.printf("%s travelled %s km\n", vehicleName, format.format(travelledKm));
                    } else {
                        System.out.printf("%s needs refueling\n", vehicleName);
                    }
                    break;
                case "Refuel":
                    try {
                        vehicle.refuel(Double.parseDouble(commandParts[2]));
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.printf("%s: %.2f\n", CAR_NAME, vehiclesByName.get(CAR_NAME).getFuelQuantity());
        System.out.printf("%s: %.2f\n", TRUCK_NAME, vehiclesByName.get(TRUCK_NAME).getFuelQuantity());
        System.out.printf("%s: %.2f\n", BUS_NAME, vehiclesByName.get(BUS_NAME).getFuelQuantity());
    }
}
