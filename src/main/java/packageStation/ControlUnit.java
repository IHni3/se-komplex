package packageStation;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.Subscriber;
import events.*;
import generate.Package;
import packageStation.command.ChangeSortingAlgorithm;
import packageStation.command.ICommand;
import packageStation.command.SearchAlgorithm;
import packageStation.sortingStation.SortingTrack;
import packageStation.sortingStation.StorageTrack;
import packageStation.sortingStation.parser.ExpressParser;
import packageStation.sortingStation.parser.NormalParser;
import packageStation.sortingStation.parser.Parser;
import packageStation.sortingStation.parser.ValueParser;
import vehicles.AutonomousCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControlUnit extends events.Subscriber {

    private final List<Package> dangerousPackages = new ArrayList<>();
    private EventBus eventBus;
    private final PackageSortingStation packageSortingStation;
    private ICommand command;
    private int filledStorageTrackCounter;
    private SearchAlgorithm searchAlgorithm;

    public ControlUnit(PackageSortingStation packageSortingStation) {
        eventBus = new EventBus("PackageSortingStation");
        this.packageSortingStation = packageSortingStation;
        filledStorageTrackCounter = 0;
        addSubscriber(this);
    }

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    private void removeSubscriber(Subscriber subscriber) {
        eventBus.unregister(subscriber);
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public SearchAlgorithm getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void init() {
    }

    public void next() {
    }

    public void shutdown() {
        packageSortingStation.shutdown();
    }

    public void lock() {
        packageSortingStation.lock();
    }

    public void unlock() {
        packageSortingStation.unlock();
    }

    public void showStatistics() {
        packageSortingStation.showStatistics();
    }

    public void chooseSearchingAlgorithmBM() {
        setCommand(new ChangeSortingAlgorithm(SearchAlgorithm.BM, packageSortingStation));
        command.execute();
    }

    public void chooseSearchingAlgorithmRK() {
        setCommand(new ChangeSortingAlgorithm(SearchAlgorithm.RK, packageSortingStation));
        command.execute();
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void touchPadBtnPressed() {
        command.execute();
    }

    public PackageSortingStation getPackageSortingStation() {
        return packageSortingStation;
    }


    public void sendEventTruckParked(int zoneID) {
        int rand = new Random().nextInt(5);
        try {
            AutonomousCar autonomousCar = packageSortingStation.getAutonomousCar(rand);
            packageSortingStation.getParkZone().removeCar(rand);
            eventBus.register(autonomousCar);
            eventBus.post(new UnloadEvent(zoneID));
            eventBus.unregister(packageSortingStation.getAutonomousCar(rand));
            packageSortingStation.getParkZone().addCar(rand, autonomousCar);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

/*
    @Subscribe
    public void sendCar(int zoneID){
        int vehicleID = ThreadLocalRandom.current().nextInt(Configuration.instance.numberOfParkingZoneAutonom);
        eventBus.post(new UnloadEvent(zoneID));
    }
    @Subscribe
    public void unloadingFinished(UnloadFinishedEvent event){
        eventBus.post(new BeginEmptyingEvent());
    }

    public void sendEventStorageTrackFilled2(int id){
        filledStorageTrackCounter++;
        if(filledStorageTrackCounter == 8){
            eventBus.post(new SortEvent());
            filledStorageTrackCounter = 0;
        }
    }
    //-----------------------------------------------------------------------------EMIL
*/


    @Subscribe
    public void robotUnload(TruckIsUnloadedEvent event) {
        try {
            System.out.println(event.getMessage());
            eventBus.post(new UnloadTemporaryStorageEvent(getPackageSortingStation().getSortingStation().getTemporaryStorage()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void storageTrackFilled(int id) {
        this.filledStorageTrackCounter++;
        if (this.filledStorageTrackCounter == 8) {
            System.out.println("All tracks are filled");
            sendEventSorting(packageSortingStation.getSortingStation().getStorageTracks());
            filledStorageTrackCounter = 0;
        } else {
            System.out.println("StorageTrack " + id + " is filled");
        }

    }

    public void sendEventSorting(StorageTrack[] storageTracks) {

        List<Package> packagesTrack = new ArrayList<>();
        for (StorageTrack t : storageTracks) {
            for (Package p : t.getPackages()) {
                packagesTrack.add(p);
            }
        }

        Parser normalParser = new NormalParser();
        Parser expressParser = new ExpressParser(normalParser);
        Parser valueParser = new ValueParser(expressParser);

        for (Package p : packagesTrack) {
            valueParser.parse(p, getPackageSortingStation().getControlUnit());
        }
        System.out.println("");
    }

    public void addDangerousPackage(Package p) {
        dangerousPackages.add(p);
    }

    public void triggerNormal(Package p) {
        addSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[0]);
        eventBus.post(new PackageToSortingTrackEvent(p, 0));
        removeSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[0]);
    }

    public void triggerValue(Package p) {
        addSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[1]);
        eventBus.post(new PackageToSortingTrackEvent(p, 1));
        removeSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[1]);
    }

    public void triggerExpress(Package p) {
        addSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[2]);
        eventBus.post(new PackageToSortingTrackEvent(p, 2));
        removeSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[2]);
    }

    public List<Package> getDangerousPackages() {
        return dangerousPackages;
    }


    // start scanning packages
    public void scan() {
        SortingTrack[] st = packageSortingStation.getSortingStation().getSortingTracks();
        for (int i = 0; i < 3; i++) {
            st[i].scan();
        }
    }
}
