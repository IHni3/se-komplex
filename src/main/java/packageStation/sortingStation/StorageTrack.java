package packageStation.sortingStation;


import main_configuration.Configuration;
import packageStation.ControlUnit;
import packageStation.sortingStation.storage_track_sensor.StorageTrackSensor;
import physicals.Package;

public class StorageTrack {
    private final Package[] packages = new Package[Configuration.instance.storageTrackCapacity];
    private final StorageTrackSensor sensor;
    private final ControlUnit controlUnit;

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
