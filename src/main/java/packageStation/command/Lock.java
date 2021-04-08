package packageStation.command;

import packageStation.PackageSortingStation;

public class Lock implements ICommand {
    private PackageSortingStation packageSortingStation;

    public Lock(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.lock();
    }
}
