package packageStation.sortingStation.storage_track_sensor;

import packageStation.ControlUnit;

public interface IStorageTrackListener {
    void trackIsFilled(ControlUnit controlUnit, int id);
}
