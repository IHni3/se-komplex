package events;

import packageStation.sortingStation.TemporaryStoragePosition;

public class UnloadTemporaryStorageEvent {
    private final TemporaryStoragePosition[] temporaryStoragePositions;

    public UnloadTemporaryStorageEvent(TemporaryStoragePosition[] tsp) {
        this.temporaryStoragePositions = tsp;
    }

    public TemporaryStoragePosition[] getTSP() {
        return temporaryStoragePositions;
    }
}
