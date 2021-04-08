package packageStation.command;

import packageStation.PackageSortingStation;

public class Init implements ICommand {
    private final PackageSortingStation packageSortingStation;


    public Init(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.init();
    }
}
