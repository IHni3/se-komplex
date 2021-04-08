package packageStation.zones.unloadingZone;

import generate.Truck;
import packageStation.ControlUnit;
import packageStation.zones.unloadingZone.unloadingZoneSensor.UnloadingZoneSensor;

public class UnloadingZone {
    private int id;
    private Truck truck;
    private final UnloadingZoneSensor sensor;

    public UnloadingZone(int id, ControlUnit setControlUnit) {
        this.id = id;
        sensor = new UnloadingZoneSensor(this.id, setControlUnit);
    }

    public void addTruck(Truck truck) {
        this.truck = truck;
        System.out.println("Truck " + truck.getTruckID() + " parked in unloading zone");
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
