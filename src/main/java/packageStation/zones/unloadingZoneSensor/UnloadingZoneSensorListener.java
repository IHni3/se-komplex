package packageStation.zones.unloadingZoneSensor;

import packageStation.ControlUnit;

public class UnloadingZoneSensorListener implements IUnloadingZoneSensorListener {

    @Override
    public void unloadingZoneSensorTriggered(int zoneID, ControlUnit controlUnit) {
        controlUnit.sendEventTruckParked(zoneID);
    }
}
