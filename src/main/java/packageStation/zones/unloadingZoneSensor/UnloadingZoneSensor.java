package packageStation.zones.unloadingZoneSensor;

import packageStation.ControlUnit;

import java.util.ArrayList;

public class UnloadingZoneSensor {

    private ArrayList<IUnloadingZoneSensorListener> listOfListeners;
    private boolean active = false;
    private final ControlUnit controlUnit;
    private final int zoneID;

    public UnloadingZoneSensor(int zoneID, ControlUnit controlUnit) {
        UnloadingZoneSensorListener unloadingZoneSensorListener = new UnloadingZoneSensorListener();
        listOfListeners = new ArrayList<>();
        this.controlUnit = controlUnit;
        this.zoneID = zoneID;
        addListener(unloadingZoneSensorListener);
    }

    public void addListener(IUnloadingZoneSensorListener listener) {
        listOfListeners.add(listener);
    }

    public void removeListener(IUnloadingZoneSensorListener listener) {
        listOfListeners.remove(listener);
    }

    public ArrayList<IUnloadingZoneSensorListener> getListOfListeners() {
        return listOfListeners;
    }

    public void setListOfListeners(ArrayList<IUnloadingZoneSensorListener> listOfListeners) {
        this.listOfListeners = listOfListeners;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void unloadingZoneSensorTriggered() {
        for (IUnloadingZoneSensorListener listener : listOfListeners) {
            listener.unloadingZoneSensorTriggered(zoneID, controlUnit);
        }
    }
}
