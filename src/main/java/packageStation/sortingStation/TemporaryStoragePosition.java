package packageStation.sortingStation;

import generate.Pallet;

public class TemporaryStoragePosition {
    private Pallet pallets[] = new Pallet[2];
    private boolean empty;

    public void addPallet(int i, Pallet pallet) {
        pallets[i] = pallet;
    }

    public Pallet getPallet(int i) {
        return pallets[i];
    }

    public void removePallet(int i) {
        pallets[i] = null;
    }

    public boolean isEmpty() {
        if (pallets[0] == null && pallets[1] == null) {
            return true;
        } else {
            return false;
        }
    }
}
