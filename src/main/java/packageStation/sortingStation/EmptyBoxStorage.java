package packageStation.sortingStation;

import generate.Box;

import java.util.ArrayList;

public class EmptyBoxStorage {
    private ArrayList<Box> emptyBoxStorage = new ArrayList<>();

    public void addBox(Box box) {
        emptyBoxStorage.add(box);
    }
}
