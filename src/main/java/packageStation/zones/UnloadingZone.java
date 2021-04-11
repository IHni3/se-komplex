package packageStation.zones;

import packageStation.ControlUnit;
import packageStation.zones.unloadingZoneSensor.UnloadingZoneSensor;
import physicals.Truck;

public class UnloadingZone {
    private final UnloadingZoneSensor sensor;
    private final int zoneID;
    private Truck truck;

    public UnloadingZone(int zoneID, ControlUnit setControlUnit) {
        this.zoneID = zoneID;
        sensor = new UnloadingZoneSensor(this.zoneID, setControlUnit);
    }

    public void addTruck(Truck truck) {
        this.truck = truck;
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
