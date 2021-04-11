package physicals;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.Subscriber;
import events.EmptyTruckEvent;
import events.UnloadTruckEvent;
import physicals.Trailer;
import packageStation.ControlUnit;
import packageStation.sortingStation.TemporaryStoragePosition;

import main_configuration.Configuration;

public class AutonomousCar extends Subscriber {
    private final EventBus eventBus;
    private ControlUnit controlUnit;

    public AutonomousCar(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void addSubscriber(Subscriber subscriber) {
        this.controlUnit = (ControlUnit) subscriber;
        eventBus.register(subscriber);
    }

    public void unloadTrailer(int zoneNumber) {
        Trailer unloadTrailer = controlUnit.getPackageSortingStation().getUnloadingZones()[zoneNumber].getTruck().getTrailer();
        TemporaryStoragePosition[] temporaryStoragePosition = controlUnit.getPackageSortingStation().getSortingStation().getTemporaryStorage();
        int palletCount = 0;

        for (TemporaryStoragePosition storagePosition : temporaryStoragePosition) {
            for (int storageCount = 0; storageCount < 2; storageCount++) {
                if (palletCount < Configuration.instance.numberOfPalletsOnTrailer/2) {
                    storagePosition.addPallet(storageCount, unloadTrailer.getLeftPallets()[palletCount]);
                } else {
                    storagePosition.addPallet(storageCount, unloadTrailer.getRightPallets()[palletCount - (Configuration.instance.numberOfPalletsOnTrailer/2)]);
                }
                palletCount++;
            }
        }
        emptyTruckEvent();
        unloadTrailer.emptyTrailer();
        controlUnit.getPackageSortingStation().addDispatchedTruck(controlUnit.getPackageSortingStation().getUnloadingZones()[zoneNumber].getTruck());
    }

    public void emptyTruckEvent() {
        eventBus.post(new EmptyTruckEvent());
    }

    @Subscribe
    public void unloadTruck(UnloadTruckEvent event) {
        try {
            unloadTrailer(event.getZoneID());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
