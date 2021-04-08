package packageStation.command;

import packageStation.PackageSortingStation;

public class ChangeSortingAlgorithm implements ICommand {
    private final PackageSortingStation packageSortingStation;
    private final SearchAlgorithm searchAlgorithm;

    public ChangeSortingAlgorithm(SearchAlgorithm searchAlgorithm, PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
        this.searchAlgorithm = searchAlgorithm;
    }

    @Override
    public void execute() {
        packageSortingStation.changeSearchingAlgorithm(searchAlgorithm);
    }
}
