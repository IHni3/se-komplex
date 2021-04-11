package packageStation.sortingStation;

import events.Subscriber;
import main_configuration.Configuration;
import packageStation.ControlUnit;
import packageStation.command.SearchAlgorithm;

public class SortingStation extends Subscriber {
    private final TemporaryStoragePosition[] temporaryStorage = new TemporaryStoragePosition[Configuration.instance.numberOfTemporaryStoragePositions];
    private final StorageEmptyBoxes storageEmptyBoxes = new StorageEmptyBoxes();
    private final StorageEmptyPallets storageEmptyPallets = new StorageEmptyPallets();
    private final StorageTrack[] storageTracks = new StorageTrack[Configuration.instance.numberOfStorageTracks];
    private final SortingTrack[] sortingTracks = new SortingTrack[Configuration.instance.numberOfSortingTracks];
    private final ControlUnit controlUnit;
    private boolean locked = false;

    public SortingStation(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
        Robot robot = new Robot(this);
        controlUnit.addSubscriber(robot);
        fillTemporaryStorage();
        fillStorageTracks();
        fillSortingTracks();
    }

    public void changeAlgorithm(SearchAlgorithm searchAlgorithm) {
        for (SortingTrack sortingTrack : sortingTracks) {
            sortingTrack.changeAlgorithm(searchAlgorithm);
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

    public StorageEmptyBoxes getStorageEmptyBoxes() {
        return storageEmptyBoxes;
    }

    public StorageEmptyPallets getStorageEmptyPallets() {
        return storageEmptyPallets;
    }

    public void unloadComponents() {
        for (SortingTrack sortingTrack : sortingTracks) {
            sortingTrack.unloadComponents();
        }
    }
}
