package packageStation.sortingStation.storage_track_sensor;

import packageStation.ControlUnit;

import java.util.ArrayList;
import java.util.List;

public class StorageTrackSensor {

    private final ControlUnit controlUnit;
    private final List<IStorageTrackListener> listOfListeners;

    public StorageTrackSensor(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
        listOfListeners = new ArrayList<>();
        IStorageTrackListener listener = new StorageTrackListener();
        addListener(listener);
    }

    public void trackIsFilled(int id) {
        for (int i = 0; i < listOfListeners.size(); i++) {
            listOfListeners.get(i).trackIsFilled(controlUnit, id);
        }
    }

    public void addListener(IStorageTrackListener listener) {
        listOfListeners.add(listener);
    }

    public void removeListener(IStorageTrackListener listener) {
        listOfListeners.remove(listener);
    }
}
