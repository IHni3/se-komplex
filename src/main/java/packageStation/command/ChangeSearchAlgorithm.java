package packageStation.command;

import packageStation.PackageSortingStation;

public class ChangeSearchAlgorithm implements ICommand {
    private final PackageSortingStation packageSortingStation;
    private final SearchAlgorithm searchAlgorithm;

    public ChangeSearchAlgorithm(SearchAlgorithm searchAlgorithm, PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
        this.searchAlgorithm = searchAlgorithm;
    }

    @Override
    public void execute() {
        packageSortingStation.changeSearchingAlgorithm(searchAlgorithm);
    }
}
