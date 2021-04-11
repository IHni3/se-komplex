package packageStation.sortingStation.storage_track_sensor;

import packageStation.ControlUnit;

public interface IStorageTrackListener {
    void trackFull(ControlUnit controlUnit, int trackID);
}
