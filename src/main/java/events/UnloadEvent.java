package events;

public class UnloadEvent {

    private final int zoneID;

    public UnloadEvent(int zoneID) {
        this.zoneID = zoneID;
    }

    public int getZoneID() {
        return zoneID;
    }
}
