package packageStation.sortingStation;

import com.google.common.eventbus.Subscribe;
import events.LoadOnSortingTrackEvent;
import events.Subscriber;
import physicals.Package;
import packageStation.ControlUnit;
import packageStation.command.SearchAlgorithm;
import packageStation.Scanner;

import java.util.ArrayList;
import java.util.List;

public class SortingTrack extends Subscriber {
    private List<Package> packages = new ArrayList<>();
    private ControlUnit controlUnit;
    private Scanner scanner;

    public SortingTrack(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    public void addPackage(Package p) {
        packages.add(p);
    }

    public List<Package> getTrack() {
        return packages;
    }

    public void scan() {
        scanner = new Scanner(controlUnit);
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
