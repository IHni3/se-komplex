package app;

import main_configuration.Configuration;
import physicals.Box;
import physicals.Package;
import physicals.Pallet;
import physicals.Truck;
import physicals.csv_generation.CSVBoxGeneration;
import physicals.csv_generation.CSVPackageGeneration;
import physicals.csv_generation.CSVPalletGeneration;
import physicals.csv_generation.CSVTruckGeneration;

import java.io.IOException;
import java.util.*;

public class Generate {


    public static void main(String... args) throws IOException {


        int counter = 0;
        Package[] packages = new Package[Configuration.instance.numberOfPackages];
        while (counter < Configuration.instance.numberOfPackages) {
            Package p = new Package();
            p.generate();
            packages[counter] = p.getPackage();
            counter++;
        }
        List<Package> listPackages = Arrays.asList(packages);
        Collections.shuffle(listPackages);
        listPackages.toArray(packages);
        Random random = new Random();
        int counterExplosivePackages = 0;
        Set<Integer> explosivePackages = new HashSet<Integer>();
        do {
            counter = random.nextInt(Configuration.instance.numberOfPackages);

            if (!explosivePackages.contains(counter)) {
                packages[counter].setToExplosive();
                counterExplosivePackages++;
            }
            explosivePackages.add(counter);
        } while (counterExplosivePackages < Configuration.instance.numberOfDangerousPackages);
        CSVPackageGeneration packageCsv = new CSVPackageGeneration();
        packageCsv.start(packages);
        int packageIndex = 0;
        Box[] boxes = new Box[Configuration.instance.numberOfBoxes];
        counter = 0;
        while (counter < Configuration.instance.numberOfBoxes) {
            Box box = new Box();
            int j = 0;
            while (j < Configuration.instance.numberOfPackagesInBox) {
                box.fillBox(packages[packageIndex]);
                packageIndex++;
                j++;
            }

            boxes[counter] = box;
            counter++;
        }
        CSVBoxGeneration boxCsv = new CSVBoxGeneration();
        boxCsv.start(boxes);
        Pallet[] pallets = new Pallet[Configuration.instance.numberOfPallets];
        int palletID = 1;
        int boxIndex = 0;
        int boxesOnPallet = Configuration.instance.numberOfBoxes / Configuration.instance.numberOfPallets;
        counter = 0;
        while (counter < Configuration.instance.numberOfPallets) {
            Pallet pallet = new Pallet(palletID);
            palletID++;
            int j = 0;
            while (j < boxesOnPallet) {
                pallet.storeBox(boxes[boxIndex]);
                boxIndex++;
                j++;
            }

            pallets[counter] = pallet;
            counter++;
        }
        CSVPalletGeneration palletCsv = new CSVPalletGeneration();
        palletCsv.start(pallets);
        Truck[] trucks = new Truck[Configuration.instance.numberOfTrucks];
        int palletIndex = 0;
        int numberOfPalletsOnTruck = Configuration.instance.numberOfPallets / Configuration.instance.numberOfTrucks;
        counter = 0;
        while (counter < Configuration.instance.numberOfTrucks) {
            Truck truck = new Truck();
            int j = 0;
            while (j < numberOfPalletsOnTruck) {
                truck.storePallet(pallets[palletIndex]);
                palletIndex++;
                j++;
            }
            trucks[counter] = truck;
            counter++;
        }
        CSVTruckGeneration truckCsv = new CSVTruckGeneration();
        truckCsv.start(trucks);
    }
}
