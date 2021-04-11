package events;

import physicals.Package;

public class LoadOnSortingTrackEvent {
    private final Package pack;
    private final int eventID;

    public LoadOnSortingTrackEvent(Package pack, int eventID) {
        this.pack = pack;
        this.eventID = eventID;
    }

    public Package getPackage() {
        return pack;
    }

    public int getEventID() {
        return eventID;
    }
}
