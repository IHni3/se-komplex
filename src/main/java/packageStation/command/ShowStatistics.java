package packageStation.command;

import packageStation.PackageSortingStation;

public class ShowStatistics implements ICommand {
    private PackageSortingStation packageSortingStation;

    //Mit show statistics wird ein Bericht mit den Informationen
    //erstellt (Builder) [i] Aktuelles Datum und Uhrzeit, [ii] Anzahl der aktuell abgefertigten LKW,
    //[iii] Anzahl der aktuell gescannten Pakete gruppiert nach type und [iv] Pakete mit gefährlichem
    //Gegenstand exp!os:ve. Dieser Bericht wird in eine Textdatei report.txt gespeichert.
    public ShowStatistics(PackageSortingStation packageSortingStation) {
        this.packageSortingStation = packageSortingStation;
    }

    @Override
    public void execute() {
        packageSortingStation.showStatistics();
    }
}
