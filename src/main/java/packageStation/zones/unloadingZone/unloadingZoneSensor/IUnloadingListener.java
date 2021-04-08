package packageStation.zones.unloadingZone.unloadingZoneSensor;

import packageStation.ControlUnit;

public interface IUnloadingListener {
    void unloadingZoneSensorTriggered(int ZoneID, ControlUnit controlUnit);
}
