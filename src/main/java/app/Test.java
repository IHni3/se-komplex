package app;

import packageStation.PackageSortingStation;

import java.io.IOException;

public class Test {


    public static void main(String... args) throws IOException {


        // initialize PackageSortingStation
        System.out.println("\n--Init");
        PackageSortingStation packageSortingStation = new PackageSortingStation();
        packageSortingStation.init();

        //next()
        System.out.println(("\n--Next"));
        packageSortingStation.next();
        packageSortingStation.next();
        packageSortingStation.next();
        packageSortingStation.next();
        packageSortingStation.next();

        packageSortingStation.getControlUnit().scan();

        //showStatistics
        System.out.println("");
        packageSortingStation.showStatistics();
    }
}
