package packageStation.zones.unloadingZoneSensor;

import packageStation.ControlUnit;

public interface IUnloadingZoneSensorListener {
    void unloadingZoneSensorTriggered(int zoneID, ControlUnit controlUnit);
}
