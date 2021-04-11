package packageStation.sortingStation;

import physicals.Box;

import java.util.ArrayList;

public class StorageEmptyBoxes {
    private final ArrayList<Box> storage = new ArrayList<>();

    public void addBox(Box box) {
        storage.add(box);
    }
}
