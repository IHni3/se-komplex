package events;

public class UnloadTruckEvent {

    private final int zoneID;

    public UnloadTruckEvent(int zoneID) {
        this.zoneID = zoneID;
    }

    public int getZoneID() {
        return zoneID;
    }
}
