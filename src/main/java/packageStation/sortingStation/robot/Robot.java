package packageStation.sortingStation.robot;

import com.google.common.eventbus.Subscribe;
import events.Subscriber;
import events.UnloadTemporaryStorageEvent;
import generate.Box;
import generate.Package;
import generate.Pallet;
import packageStation.sortingStation.SortingStation;
import packageStation.sortingStation.StorageTrack;
import packageStation.sortingStation.TemporaryStoragePosition;

public class Robot extends Subscriber {
    StorageTrack[] storageTracks;
    int packageCount;
    SortingStation sortingStation;

    public Robot(SortingStation sortingStation) {
        this.sortingStation = sortingStation;
        this.storageTracks = this.sortingStation.getStorageTracks();
    }

    @Subscribe
    public void palletsToStorageTrack(UnloadTemporaryStorageEvent event) {
        TemporaryStoragePosition[] temporaryStoragePosition = event.getTSP();
        packageCount = 0;
        for (int x = 0; x < temporaryStoragePosition.length; x++) {
            for (int y = 0; y < 2; y++) {
                Pallet pallet = temporaryStoragePosition[x].getPallet(y);
                for (int a = 0; a < 2; a++) {
                    for (int b = 0; b < 2; b++) {
                        for (int c = 0; c < 3; c++) {
                            Box box = pallet.getPosition()[a][b].getBoxs()[c];
                            for (int d = 0; d < 40; d++) {
                                Package p = box.getPackage(d);
                                if (packageCount < 600) {
                                    storageTracks[0].setPackages(packageCount, p);
                                    packageCount++;
                                } else if (packageCount < 1200) {
                                    storageTracks[1].setPackages(packageCount - 600, p);
                                    packageCount++;
                                } else if (packageCount < 1800) {
                                    storageTracks[2].setPackages(packageCount - 1200, p);
                                    packageCount++;
                                } else if (packageCount < 2400) {
                                    storageTracks[3].setPackages(packageCount - 1800, p);
                                    packageCount++;
                                } else if (packageCount < 3000) {
                                    storageTracks[4].setPackages(packageCount - 2400, p);
                                    packageCount++;
                                } else if (packageCount < 3600) {
                                    storageTracks[5].setPackages(packageCount - 3000, p);
                                    packageCount++;
                                } else if (packageCount < 4200) {
                                    storageTracks[6].setPackages(packageCount - 3600, p);
                                    packageCount++;
                                } else if (packageCount < 4800) {
                                    storageTracks[7].setPackages(packageCount - 4200, p);
                                    packageCount++;
                                }
                                switch (packageCount) {
                                    case 600 -> storageTracks[0].triggerSensor(0);
                                    case 1200 -> storageTracks[1].triggerSensor(1);
                                    case 1800 -> storageTracks[2].triggerSensor(2);
                                    case 2400 -> storageTracks[3].triggerSensor(3);
                                    case 3000 -> storageTracks[4].triggerSensor(4);
                                    case 3600 -> storageTracks[5].triggerSensor(5);
                                    case 4200 -> storageTracks[6].triggerSensor(6);
                                    case 4800 -> storageTracks[7].triggerSensor(7);
                                    default -> {
                                        break;
                                    }
                                }
                            }
                            box.removeAll();
                            sortingStation.getEmptyBoxStorage().addBox(box);
                            pallet.getPosition()[a][b].setBox(c, null);
                        }
                    }
                }
                pallet.resetCounter();
                sortingStation.getEmptyPalletStorage().addPallet(pallet);
                temporaryStoragePosition[x].removePallet(y);
            }
        }
    }
}
