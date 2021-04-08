package packageStation.zones.unloadingZone.unloadingZoneSensor;

import packageStation.ControlUnit;

public class UnloadingListener implements IUnloadingListener {

    @Override
    public void unloadingZoneSensorTriggered(int ZoneID, ControlUnit controlUnit) {
        controlUnit.sendEventTruckParked(ZoneID);
    }
}
