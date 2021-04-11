package packageStation.sortingStation;

import com.google.common.eventbus.Subscribe;
import events.LoadOnSortingTrackEvent;
import events.Subscriber;
import packageStation.ControlUnit;
import packageStation.Scanner;
import packageStation.command.SearchAlgorithm;
import physicals.Package;

import java.util.ArrayList;
import java.util.List;

public class SortingTrack extends Subscriber {
    private final List<Package> packages = new ArrayList<>();
    private final ControlUnit controlUnit;
    private final Scanner scanner;

    public SortingTrack(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
        scanner = new Scanner(controlUnit);
    }

    public void addPackage(Package p) {
        packages.add(p);
    }

    public List<Package> getTrack() {
        return packages;
    }

    public void scan() {

        for (Package p : packages) {

            scanner.search(p);


        }

    }

    public void unloadComponents() {
        scanner.unloadComponents();
    }


    @Subscribe
    public void incomingPackage(LoadOnSortingTrackEvent event) {
        addPackage(event.getPackage());
        controlUnit.getPackageSortingStation().setNumberOfPackagesGroupedByType(event.getEventID());
    }

    public void changeAlgorithm(SearchAlgorithm searchAlgorithm) {
        scanner.changeSearchAlgorithm(searchAlgorithm);
    }
}
