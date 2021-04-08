package packageStation.sortingStation.storageTrackSensor;

import packageStation.ControlUnit;

public class StorageTrackListener implements IStorageTrackListener {
    @Override
    public void trackIsFilled(ControlUnit controlUnit, int id) {
        controlUnit.storageTrackFilled(id);
    }
}
