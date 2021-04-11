package packageStation.sortingStation.robot;

import com.google.common.eventbus.Subscribe;
import events.Subscriber;
import events.UnloadTemporaryStorageEvent;
import physicals.Box;
import physicals.Package;
import physicals.Pallet;
import packageStation.sortingStation.SortingStation;
import packageStation.sortingStation.StorageTrack;
import packageStation.sortingStation.TemporaryStoragePosition;
import main_configuration.Configuration;

public class Robot extends Subscriber {
    private final StorageTrack[] storageTracks;
    private final SortingStation sortingStation;

    public Robot(SortingStation sortingStation) {
        this.sortingStation = sortingStation;
        this.storageTracks = this.sortingStation.getStorageTracks();
    }

    @Subscribe
    public void palletsToStorageTrack(UnloadTemporaryStorageEvent event) {
        TemporaryStoragePosition[] temporaryStoragePosition = event.getTSP();
        int packageCount = 0;
        int storageTrackCount=0;
        for (int x = 0; x < temporaryStoragePosition.length; x++) {
            for (int y = 0; y < 2; y++) {
                Pallet pallet = temporaryStoragePosition[x].getPallet(y);
                for (int a = 0; a < 2; a++) {
                    for (int b = 0; b < 2; b++) {
                        for (int c = 0; c < Configuration.instance.numberOfPalletLevels; c++) {
                            Box box = pallet.getPosition()[a][b].getBoxArray()[c];
                            for (int d = 0; d < Configuration.instance.numberOfPackagesInBox; d++) {
                                Package p = box.getPackage(d);
                                if(packageCount<Configuration.instance.storageTrackCapacity)
                                {
                                    if(storageTrackCount<Configuration.instance.numberOfStorageTracks) {
                                        storageTracks[storageTrackCount].setPackages(packageCount, p);

                                        if (packageCount == Configuration.instance.storageTrackCapacity - 1) {
                                            storageTracks[storageTrackCount].triggerSensor(storageTrackCount);
                                            storageTrackCount++;
                                            packageCount = 0;
                                        }
                                        else {
                                            packageCount++;
                                        }
                                    }
                                }
                            }
                            box.emptyBox();
                            sortingStation.getStorageEmptyBoxes().addBox(box);
                            pallet.getPosition()[a][b].setBox(c, null);
                        }
                    }
                }
                pallet.resetCounter();
                sortingStation.getStorageEmptyPallets().addPallet(pallet);
                temporaryStoragePosition[x].removePallet(y);
            }
        }
    }
}
