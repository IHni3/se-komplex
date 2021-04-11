package packageStation;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.*;
import main_configuration.Configuration;
import packageStation.command.ChangeSearchAlgorithm;
import packageStation.command.ICommand;
import packageStation.command.SearchAlgorithm;
import packageStation.sortingStation.SortingTrack;
import packageStation.sortingStation.StorageTrack;
import packageStation.sortingStation.parser.ExpressParser;
import packageStation.sortingStation.parser.NormalParser;
import packageStation.sortingStation.parser.Parser;
import packageStation.sortingStation.parser.ValueParser;
import physicals.AutonomousCar;
import physicals.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControlUnit extends events.Subscriber {

    private final List<Package> dangerousPackages = new ArrayList<>();
    private final PackageSortingStation packageSortingStation;
    private EventBus eventBus;
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
        setCommand(new ChangeSearchAlgorithm(SearchAlgorithm.BM, packageSortingStation));
        command.execute();
    }

    public void chooseSearchingAlgorithmRK() {
        setCommand(new ChangeSearchAlgorithm(SearchAlgorithm.RK, packageSortingStation));
        command.execute();
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void touchPadUsed() {
        command.execute();
    }

    public PackageSortingStation getPackageSortingStation() {
        return packageSortingStation;
    }


    public void sendEventTruckParked(int zoneID) {
        int random = new Random().nextInt(Configuration.instance.numberOfAutonomousCars);
        try {
            AutonomousCar autonomousCar = packageSortingStation.getAutonomousCar(random);
            packageSortingStation.getParkZone().removeCar(random);
            eventBus.register(autonomousCar);
            eventBus.post(new UnloadTruckEvent(zoneID));
            eventBus.unregister(packageSortingStation.getAutonomousCar(random));
            packageSortingStation.getParkZone().addCar(random, autonomousCar);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Subscribe
    public void robotUnload(EmptyTruckEvent event) {
        try {
            System.out.println(event.getMessage());
            eventBus.post(new UnloadTemporaryStorageEvent(getPackageSortingStation().getSortingStation().getTemporaryStorage()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void storageTrackFilled(int id) {
        this.filledStorageTrackCounter++;
        if (this.filledStorageTrackCounter == Configuration.instance.numberOfStorageTracks) {
            System.out.println("StorageTrack " + id + " is full");
            System.out.println("All Storage Tracks are full.");
            sendEventSorting(packageSortingStation.getSortingStation().getStorageTracks());
            filledStorageTrackCounter = 0;
        } else {
            System.out.println("StorageTrack " + id + " is full");
        }

    }

    public void sendEventSorting(StorageTrack[] storageTracks) {

        List<Package> packagesTrack = new ArrayList<>();
        for (StorageTrack storageTrack : storageTracks) {
            for (Package p : storageTrack.getPackages()) {
                packagesTrack.add(p);
            }
        }

        Parser normalParser = new NormalParser();
        Parser expressParser = new ExpressParser(normalParser);
        Parser valueParser = new ValueParser(expressParser);

        for (Package p : packagesTrack) {
            valueParser.parse(p, getPackageSortingStation().getControlUnit());
        }
    }

    public void addDangerousPackage(Package p) {
        dangerousPackages.add(p);
    }

    public void normalPackage(Package p) {
        addSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[0]);
        eventBus.post(new LoadOnSortingTrackEvent(p, 0));
        removeSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[0]);
    }

    public void valuePackage(Package p) {
        addSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[1]);
        eventBus.post(new LoadOnSortingTrackEvent(p, 1));
        removeSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[1]);
    }

    public void expressPackage(Package p) {
        addSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[2]);
        eventBus.post(new LoadOnSortingTrackEvent(p, 2));
        removeSubscriber(packageSortingStation.getSortingStation().getSortingTracks()[2]);
    }

    public List<Package> getDangerousPackages() {
        return dangerousPackages;
    }


    public void scan() {
        SortingTrack[] sortingTracks = packageSortingStation.getSortingStation().getSortingTracks();
        for (int i = 0; i < Configuration.instance.numberOfSortingTracks; i++) {
            sortingTracks[i].scan();
        }
    }
}
