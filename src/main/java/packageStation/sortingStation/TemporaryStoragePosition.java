package packageStation.sortingStation;

import physicals.Pallet;
import main_configuration.Configuration;

public class TemporaryStoragePosition {
    private Pallet pallets[] = new Pallet[Configuration.instance.numberOfTemporaryStorageLayers];
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
        for(int i=0; i<Configuration.instance.numberOfTemporaryStorageLayers; i++)
        {
            if(pallets[i] !=null)
            {
                return false;
            }
        }
        return true;
    }
}
