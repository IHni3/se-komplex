package packageStation.sortingStation;

import generate.Pallet;

import java.util.ArrayList;

public class EmptyPalletStorage {
    private ArrayList<Pallet> emptyPalletStorage = new ArrayList<>();

    public void addPallet(Pallet pallet) {
        emptyPalletStorage.add(pallet);
    }
}
