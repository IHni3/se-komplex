package packageStation.command;

import packageStation.PackageSortingStation;

public class Next implements ICommand {
    PackageSortingStation packageSortingStation;

    public Next(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
        System.out.println();

    }

    @Override
    public void execute() {
        packageSortingStation.next();
    }
}
