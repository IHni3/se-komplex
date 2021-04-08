package packageStation.command;

import packageStation.PackageSortingStation;

public class Lock implements ICommand {
    private final PackageSortingStation packageSortingStation;

    public Lock(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.lock();
    }
}
