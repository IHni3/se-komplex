package packageStation.zones;

import physicals.Truck;
import packageStation.ControlUnit;
import packageStation.zones.unloadingZoneSensor.UnloadingZoneSensor;

public class UnloadingZone {
    private int zoneID;
    private Truck truck;
    private final UnloadingZoneSensor sensor;

    public UnloadingZone(int zoneID, ControlUnit setControlUnit) {
        this.zoneID = zoneID;
        sensor = new UnloadingZoneSensor(this.zoneID, setControlUnit);
    }

    public void addTruck(Truck truck) {
        this.truck = truck;
        System.out.println(zoneID + ": Truck "+ truck.getTruckID() + " arrived.");
        sensor.unloadingZoneSensorTriggered();
    }

    public void removeTruck() {
        truck = null;
    }

    public Truck getTruck() {
        return truck;
    }

    public void turnOffSensor() {
        sensor.setActive(false);
    }

    public UnloadingZoneSensor getSensor() {
        return sensor;
    }
}
