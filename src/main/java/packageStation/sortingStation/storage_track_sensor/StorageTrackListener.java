package packageStation.sortingStation.storage_track_sensor;

import packageStation.ControlUnit;

public class StorageTrackListener implements IStorageTrackListener {
    @Override
    public void trackFull(ControlUnit controlUnit, int trackID) {
        controlUnit.storageTrackFilled(trackID);
    }
}
