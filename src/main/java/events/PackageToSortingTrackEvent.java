package events;

import generate.Package;

public class PackageToSortingTrackEvent {
    private final Package p;
    private final int id;

    public PackageToSortingTrackEvent(Package p, int i) {
        this.p = p;
        this.id = i;
    }

    public Package getPackage() {
        return p;
    }

    public int getID() {
        return id;
    }
}
