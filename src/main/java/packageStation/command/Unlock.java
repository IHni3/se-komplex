package packageStation.command;

import packageStation.PackageSortingStation;

public class Unlock implements ICommand {
    private PackageSortingStation packageSortingStation;

    public Unlock(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.unlock();
    }
}
