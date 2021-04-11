package packageStation.sortingStation;

import com.google.common.eventbus.Subscribe;
import events.Subscriber;
import events.UnloadTemporaryStorageEvent;
import main_configuration.Configuration;
import physicals.Box;
import physicals.Package;
import physicals.Pallet;

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
        int storageTrackCount = 0;
        for (TemporaryStoragePosition storagePosition : temporaryStoragePosition) {
            int y = 0;
            while (y < 2) {
                Pallet pallet = storagePosition.getPallet(y);
                int a = 0;
                while (a < 2) {
                    int b = 0;
                    while (b < 2) {
                        int c = 0;
                        while (c < Configuration.instance.numberOfPalletLevels) {
                            Box box = pallet.getPosition()[a][b].getBoxArray()[c];
                            int d = 0;
                            while (d < Configuration.instance.numberOfPackagesInBox) {
                                Package p = box.getPackage(d);
                                if (packageCount < Configuration.instance.storageTrackCapacity) {
                                    if (storageTrackCount < Configuration.instance.numberOfStorageTracks) {
                                        storageTracks[storageTrackCount].setPackages(packageCount, p);

                                        if (packageCount == Configuration.instance.storageTrackCapacity - 1) {
                                            storageTracks[storageTrackCount].triggerSensor(storageTrackCount);
                                            storageTrackCount++;
                                            packageCount = 0;
                                        } else {
                                            packageCount++;
                                        }
                                    }
                                }
                                d++;
                            }
                            box.emptyBox();
                            sortingStation.getStorageEmptyBoxes().addBox(box);
                            pallet.getPosition()[a][b].setBox(c, null);
                            c++;
                        }
                        b++;
                    }
                    a++;
                }
                pallet.resetCounter();
                sortingStation.getStorageEmptyPallets().addPallet(pallet);
                storagePosition.removePallet(y);
                y++;
            }
        }
    }
}
