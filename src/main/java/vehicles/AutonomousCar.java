package vehicles;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.Subscriber;
import events.TruckIsUnloadedEvent;
import events.UnloadEvent;
import generate.Trailer;
import packageStation.ControlUnit;
import packageStation.sortingStation.TemporaryStoragePosition;

public class AutonomousCar extends Subscriber {
    private EventBus eventBus;
    private ControlUnit controlUnit;

    public AutonomousCar(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void addSubscriber(Subscriber subscriber) {
        this.controlUnit = (ControlUnit) subscriber;
        eventBus.register(subscriber);
    }

    public void unloadTrailer(int i) {
        Trailer unloadTrailer = controlUnit.getPackageSortingStation().getUnloadingZones()[i].getTruck().getTrailer();
        TemporaryStoragePosition[] temporaryStoragePosition = controlUnit.getPackageSortingStation().getSortingStation().getTemporaryStorage();
        int palletCount = 0;

        for (int positionCount = 0; positionCount < temporaryStoragePosition.length; positionCount++) {
            for (int storageCount = 0; storageCount < 2; storageCount++) {
                if (palletCount < 5) {
                    temporaryStoragePosition[positionCount].addPallet(storageCount, unloadTrailer.getLeftPallets()[palletCount]);
                    palletCount++;
                } else {
                    temporaryStoragePosition[positionCount].addPallet(storageCount, unloadTrailer.getRightPallets()[palletCount - 5]);
                    palletCount++;
                }
            }
        }
        unloadedTruckEvent();
        unloadTrailer.emptyTrailer();
        controlUnit.getPackageSortingStation().addDispatchedTruck(controlUnit.getPackageSortingStation().getUnloadingZones()[i].getTruck());
    }

    public void unloadedTruckEvent() {
        eventBus.post(new TruckIsUnloadedEvent());
    }

    @Subscribe
    public void unloadTruck(UnloadEvent event) {
        try {
            unloadTrailer(event.getZoneID());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
