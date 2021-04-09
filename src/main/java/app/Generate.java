package app;

import generate.Package;
import generate.*;
import main_configuration.Configuration;

import java.io.IOException;
import java.util.*;

public class Generate {


    public static void main(String... args) throws IOException {



        // Generate Packages
        Package[] packages = new Package[Configuration.instance.numberOfPackages];
        for (int i = 0; i < Configuration.instance.numberOfPackages; i++) {
            Package p = new Package();
            p.generate();
            packages[i] = p.getPackage();
        }

        // shuffle packages
        List<Package> listPackages = Arrays.asList(packages);
        Collections.shuffle(listPackages);
        listPackages.toArray(packages);

        // Set four packages to explosive
        Random random = new Random();
        int counterExplosivePackages=0;
        Set <Integer> explosivePackages = new HashSet<Integer>();
        do
        {
            int j= random.nextInt(Configuration.instance.numberOfPackages);

            if(!explosivePackages.contains(j))
            {
               packages[j].setToExplosive();
               counterExplosivePackages++;
            }
            explosivePackages.add(j);
        }while(counterExplosivePackages<=3);

        // Generate base_package.csv
        CSVPackageGeneration packageCsv = new CSVPackageGeneration();
        packageCsv.start(packages);

        // Generate Boxes and sorting Packages to them
        int packageIndex = 0;
        Box[] boxes = new Box[Configuration.instance.numberOfBoxes];
        for (int i = 0; i < Configuration.instance.numberOfBoxes; i++) {
            Box box = new Box();
            for (int j = 0; j < Configuration.instance.numberOfPackagesInBox; j++) {
                box.fillBox(packages[packageIndex]);
                packageIndex++;
            }
            boxes[i] = box;
        }


        // Generate base_box.csv
        CSVBoxGeneration boxCsv = new CSVBoxGeneration();
        boxCsv.start(boxes);

        // Generate Pallets and sorting Boxes to them
        Pallet[] pallets = new Pallet[Configuration.instance.numberOfPallets];
        int palletID = 1;
        int boxIndex = 0;
        int boxesOnPallet = Configuration.instance.numberOfBoxes/Configuration.instance.numberOfPallets;
        for (int i = 0; i < Configuration.instance.numberOfPallets; i++) {
            Pallet pallet = new Pallet(palletID);
            palletID++;
            for (int j = 0; j < boxesOnPallet; j++) {
                pallet.storeBox(boxes[boxIndex]);
                boxIndex++;
            }
            pallets[i] = pallet;
        }

        // Generate base_pallet.csv
        CSVPalletGeneration palletCsv = new CSVPalletGeneration();
        palletCsv.start(pallets);

        // Generate Trucks with trailer and fill them with pallets
        Truck[] trucks = new Truck[Configuration.instance.numberOfTrucks];
        int palletIndex=0;
        int numberOfPalletsOnTruck = Configuration.instance.numberOfPallets/Configuration.instance.numberOfTrucks;
        for (int i = 0; i < Configuration.instance.numberOfTrucks; i++) {
            Truck truck = new Truck();
            for (int j = 0; j < numberOfPalletsOnTruck; j++) {
                truck.storePallet(pallets[palletIndex]);
                palletIndex++;
            }
            trucks[i] = truck;
        }

        // Generate base_truck.csv
        CSVTruckGeneration truckCsv = new CSVTruckGeneration();
        truckCsv.start(trucks);
    }
}
