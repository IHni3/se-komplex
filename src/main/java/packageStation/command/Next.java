package packageStation.command;

import packageStation.PackageSortingStation;

public class Next implements ICommand {
    private final PackageSortingStation packageSortingStation;

    public Next(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.next();
    }
}
