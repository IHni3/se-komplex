package packageStation.zones;

import main_configuration.Configuration;
import physicals.AutonomousCar;

public class ParkingZone {
    private final AutonomousCar[] autonomousCars;

    public ParkingZone() {
        autonomousCars = new AutonomousCar[Configuration.instance.numberOfAutonomousCars];
    }

    public void removeCar(int i) {
        autonomousCars[i] = null;
    }

    public void addCar(int i, AutonomousCar autonomousCar) {
        autonomousCars[i] = autonomousCar;
    }

    public AutonomousCar[] getAutonomousCars() {
        return autonomousCars;
    }
}
