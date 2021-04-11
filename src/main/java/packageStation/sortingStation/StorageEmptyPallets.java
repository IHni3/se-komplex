package packageStation.sortingStation;

import physicals.Pallet;

import java.util.ArrayList;

public class StorageEmptyPallets {
    private final ArrayList<Pallet> storage = new ArrayList<>();

    public void addPallet(Pallet pallet) {
        storage.add(pallet);
    }
}
