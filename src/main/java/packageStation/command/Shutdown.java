package packageStation.command;

import packageStation.PackageSortingStation;

public class Shutdown implements ICommand {
    private PackageSortingStation packageSortingStation;

    //Mit shutdown werden die Sensoren an der Zone für
    //die Entladung der LKW deaktiviert sowie die Komponenten bei den Scanner entladen. Mit lock und
    //unlock wird die Sortieranlage ge-/entsperrt.
    public Shutdown(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.shutdown();
    }
}
