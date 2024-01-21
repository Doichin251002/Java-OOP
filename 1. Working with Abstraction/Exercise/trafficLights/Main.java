package trafficLights;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String [] inputLights = scan.nextLine().split("\\s+");

        TrafficLight [] trafficLights = new TrafficLight[inputLights.length];
        for (int i = 0; i < inputLights.length; i++) {
            String l = inputLights[i];
            TrafficLight.Signal signal = TrafficLight.Signal.valueOf(l);
            trafficLights[i] = new TrafficLight(signal);
        }
        int n = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < n; i++) {
            updateTrafficLights(trafficLights);
            printTrafficLights(trafficLights);
        }
    }
    private static void updateTrafficLights(TrafficLight[] trafficLights) {
        for (TrafficLight trafficLight : trafficLights) {
            trafficLight.update();
        }
    }
    private static void printTrafficLights(TrafficLight[] trafficLights) {
        for (TrafficLight trafficLight : trafficLights) {
            System.out.print(trafficLight.getSignal() + " ");
        }
        System.out.println();
    }
}
