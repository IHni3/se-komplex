package packageStation.zones.unloadingZone;

import generate.Truck;
import packageStation.ControlUnit;
import packageStation.zones.unloadingZone.unloadingZoneSensor.UnloadingZoneSensor;

public class UnloadingZone {
    private int id;
    private Truck truck;
    private UnloadingZoneSensor sensor;
    private ControlUnit controlUnit;

    public UnloadingZone(int id, ControlUnit setControlUnit) {
        this.id = id;
        this.controlUnit = setControlUnit;
        sensor = new UnloadingZoneSensor(this.id, controlUnit);
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
