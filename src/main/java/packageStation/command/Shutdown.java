package packageStation.command;

import packageStation.PackageSortingStation;

public class Shutdown implements ICommand {
    private final PackageSortingStation packageSortingStation;

    public Shutdown(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.shutdown();
    }
}
