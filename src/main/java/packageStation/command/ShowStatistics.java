package packageStation.command;

import packageStation.PackageSortingStation;

public class ShowStatistics implements ICommand {
    private final PackageSortingStation packageSortingStation;

    public ShowStatistics(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.showStatistics();
    }
}
