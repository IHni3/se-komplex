package packageStation.zones;

import vehicles.AutonomousCar;

public class ParkZone {
    private final AutonomousCar[] autonomousCars;

    public ParkZone() {
        autonomousCars = new AutonomousCar[5];
    }

    public void removeCar(int i) {
        autonomousCars[i] = null;
        System.out.println("sendCar " + i);
    }

    public void addCar(int i, AutonomousCar autonomousCar) {
        autonomousCars[i] = autonomousCar;
        System.out.println("AutonomousCar " + i + " is back");
    }

    public AutonomousCar[] getAutonomousCars() {
        return autonomousCars;
    }
}
