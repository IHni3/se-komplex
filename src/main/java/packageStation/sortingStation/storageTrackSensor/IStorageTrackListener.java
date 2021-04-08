package packageStation.sortingStation.storageTrackSensor;

import packageStation.ControlUnit;

public interface IStorageTrackListener {
    void trackIsFilled(ControlUnit controlUnit, int id);
}
