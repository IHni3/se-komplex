package packageStation.sortingStation;

import main_configuration.Configuration;
import physicals.Pallet;

public class TemporaryStoragePosition {
    private final Pallet[] pallets = new Pallet[Configuration.instance.numberOfTemporaryStorageLayers];
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
        int i = 0;
        while (i < Configuration.instance.numberOfTemporaryStorageLayers) {
            if (pallets[i] != null) {
                return false;
            }
            i++;
        }
        return true;
    }
}
