package packageStation.sortingStation;

import com.google.common.eventbus.Subscribe;
import events.PackageToSortingTrackEvent;
import events.Subscriber;
import generate.Package;
import packageStation.ControlUnit;
import packageStation.command.SearchAlgorithm;
import scanner.Scanner;

import java.util.ArrayList;
import java.util.List;

public class SortingTrack extends Subscriber {
    private List<Package> packages = new ArrayList<>();
    private ControlUnit cu;
    private Scanner scanner;

    public SortingTrack(ControlUnit cu) {
        this.cu = cu;
    }

    public void addPackage(Package p) {
        packages.add(p);
    }

    public List<Package> getTrack() {
        return packages;
    }

    public void scan() {
        scanner = new Scanner(cu);
        for (Package p : packages) {
            scanner.search(p);
        }
    }

    public void unloadComponents() {
        scanner.unloadComponents();
    }


    @Subscribe
    public void incomingPackage(PackageToSortingTrackEvent event) {
        addPackage(event.getPackage());
        cu.getPackageSortingStation().setNumberOfPackagesGroupedByType(event.getID());
    }

    public void changeSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        scanner.changeSearchAlgorithm(searchAlgorithm);
    }
}
