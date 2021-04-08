package packageStation.zones.unloadingZone.unloadingZoneSensor;

import packageStation.ControlUnit;

public class UnloadingListener implements IUnloadingListener {
    private ControlUnit controlUnit;

    @Override
    public void unloadingZoneSensorTriggered(int ZoneID, ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
        controlUnit.sendEventTruckParked(ZoneID);
    }
}
