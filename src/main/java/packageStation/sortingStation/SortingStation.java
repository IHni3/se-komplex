package packageStation.sortingStation;

import events.Subscriber;
import packageStation.ControlUnit;
import packageStation.command.SearchAlgorithm;
import packageStation.sortingStation.robot.Robot;

public class SortingStation extends Subscriber {
    private boolean locked = false;
    private final TemporaryStoragePosition[] temporaryStorage;
    private final EmptyBoxStorage emptyBoxStorage;
    private final EmptyPalletStorage emptyPalletStorage;
    private final StorageTrack[] storageTracks;
    private final SortingTrack[] sortingTracks;
    private final ControlUnit controlUnit;

    public SortingStation(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
        emptyBoxStorage = new EmptyBoxStorage();
        emptyPalletStorage = new EmptyPalletStorage();
        temporaryStorage = new TemporaryStoragePosition[5];
        storageTracks = new StorageTrack[8];
        sortingTracks = new SortingTrack[3];
        Robot robot = new Robot(this);
        controlUnit.addSubscriber(robot);
        fillTemporaryStorage();
        fillStorageTracks();
        fillSortingTracks();
    }

    public void changeSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        for (SortingTrack sortingTrack : sortingTracks) {
            sortingTrack.changeSearchAlgorithm(searchAlgorithm);
        }


    }

    public void fillTemporaryStorage() {
        for (int i = 0; i < temporaryStorage.length; i++) {
            TemporaryStoragePosition temporaryStoragePosition = new TemporaryStoragePosition();
            temporaryStorage[i] = temporaryStoragePosition;
        }
    }

    public void fillStorageTracks() {
        for (int i = 0; i < storageTracks.length; i++) {
            StorageTrack storageTrack = new StorageTrack(controlUnit);
            storageTracks[i] = storageTrack;
        }
    }


    //      KP was das ist, in Sorting Tracks sind jetzt 3 Listen für die Bahnen.
    public void fillSortingTracks() {
        for (int i = 0; i < sortingTracks.length; i++) {
            SortingTrack sortingTrack = new SortingTrack(controlUnit);
            sortingTracks[i] = sortingTrack;
        }
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public StorageTrack[] getStorageTracks() {
        return storageTracks;
    }

    public TemporaryStoragePosition[] getTemporaryStorage() {
        return temporaryStorage;
    }

    public SortingTrack[] getSortingTracks() {
        return sortingTracks;
    }

    public EmptyBoxStorage getEmptyBoxStorage() {
        return emptyBoxStorage;
    }

    public EmptyPalletStorage getEmptyPalletStorage() {
        return emptyPalletStorage;
    }

    /*public void addPackage (int i, Package p) {
            sortingTracks[i].addPackage(p);
    }
     */

    public void unloadComponents() {
        for (SortingTrack sortingTrack : sortingTracks) {
            sortingTrack.unloadComponents();
        }
    }
}
