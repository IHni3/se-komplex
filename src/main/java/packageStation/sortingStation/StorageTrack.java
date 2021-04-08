package packageStation.sortingStation;


import generate.Package;
import packageStation.ControlUnit;
import packageStation.sortingStation.storageTrackSensor.StorageTrackSensor;

public class StorageTrack {
    private Package packages[];
    private StorageTrackSensor sensor;
    private ControlUnit controlUnit;

    public StorageTrack(ControlUnit controlUnit) {
        packages = new Package[600];
        this.controlUnit = controlUnit;
        sensor = new StorageTrackSensor(this.controlUnit);
    }

    public void setPackages(int i, Package p) {
        packages[i] = p;
    }

    public Package[] getPackages() {
        return packages;
    }

    public void triggerSensor(int id) {
        sensor.trackIsFilled(id);
    }
}
