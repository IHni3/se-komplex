package packageStation.zones.unloadingZone.unloadingZoneSensor;

import packageStation.ControlUnit;

import java.util.ArrayList;

public class UnloadingZoneSensor {

    private ArrayList<IUnloadingListener> listOfListeners;
    private boolean active = false;
    private ControlUnit controlUnit;
    private int id;
    private UnloadingListener unloadingListener;

    public UnloadingZoneSensor(int id, ControlUnit controlUnit) {
        unloadingListener = new UnloadingListener();
        listOfListeners = new ArrayList<>();
        this.controlUnit = controlUnit;
        this.id = id;
        addListener(unloadingListener);
    }

    public void addListener(IUnloadingListener listener) {
        listOfListeners.add(listener);
    }

    public void removeListener(IUnloadingListener listener) {
        listOfListeners.remove(listener);
    }

    public ArrayList<IUnloadingListener> getListOfListeners() {
        return listOfListeners;
    }

    public void setListOfListeners(ArrayList<IUnloadingListener> listOfListeners) {
        this.listOfListeners = listOfListeners;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void unloadingZoneSensorTriggered() {
        for (IUnloadingListener currentlistener : listOfListeners) {
            currentlistener.unloadingZoneSensorTriggered(id, controlUnit);
        }
    }
}
