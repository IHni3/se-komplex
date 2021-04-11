package packageStation.sortingStation;


import physicals.Package;
import main_configuration.Configuration;
import packageStation.ControlUnit;
import packageStation.sortingStation.storage_track_sensor.StorageTrackSensor;

public class StorageTrack {
    private Package packages[] = new Package[Configuration.instance.storageTrackCapacity];
    private StorageTrackSensor sensor;
    private ControlUnit controlUnit;

    public StorageTrack(ControlUnit controlUnit) {
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
        sensor.trackFull(id);
    }
}
