package packageStation.sortingStation.storage_track_sensor;

import packageStation.ControlUnit;

public class StorageTrackListener implements IStorageTrackListener {
    @Override
    public void trackIsFilled(ControlUnit controlUnit, int id) {
        controlUnit.storageTrackFilled(id);
    }
}
