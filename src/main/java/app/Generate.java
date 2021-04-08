package app;

import generate.Package;
import generate.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generate {


    public static void main(String... args) throws IOException {

        int index = 0;

        // Generate Packages
        Package[] packages = new Package[24000];
        for (int i = 0; i < 24000; i++) {
            Package p = new Package();
            p.generate();
            packages[i] = p.getPackage();
        }

        // shuffle packages
        List<Package> list = Arrays.asList(packages);
        Collections.shuffle(list);
        list.toArray(packages);

        // Set four packages to explosive
        packages[1453].setToExplosive();
        packages[1815].setToExplosive();
        packages[1942].setToExplosive();
        packages[14697].setToExplosive();

        // Generate base_package.csv
        ExportToCSVPackage packageCsv = new ExportToCSVPackage();
        packageCsv.start(packages);

        // Generate Boxes and sorting Packages to them
        Box[] boxes = new Box[600];
        for (int j = 0; j < 600; j++) {
            Box box = new Box();
            box.generateID();
            for (int i = 0; i < 40; i++) {
                box.fillLvl(packages[index]);
                index++;
            }
            boxes[j] = box;
        }
        index = 0;

        // Generate base_box.csv
        ExportToCSVBox boxCsv = new ExportToCSVBox();
        boxCsv.start(boxes);

        // Generate Pallets and sorting Boxes to them
        Pallet[] pallets = new Pallet[50];
        int palletCounter = 1;
        for (int i = 0; i < 50; i++) {
            Pallet p = new Pallet(palletCounter);
            palletCounter++;
            for (int j = 0; j < 12; j++) {
                p.storeBox(boxes[index]);
                index++;
            }
            pallets[i] = p;
        }
        index = 0;

        // Generate base_pallet.csv
        ExportToCSVPallet palletCsv = new ExportToCSVPallet();
        palletCsv.start(pallets);

        // Generate Trucks with trailer and fill them with pallets
        Truck[] trucks = new Truck[5];
        for (int i = 0; i < 5; i++) {
            Truck t = new Truck();
            for (int j = 0; j < 10; j++) {
                t.storePallet(pallets[index]);
                index++;
            }
            trucks[i] = t;
        }
        index = 0;

        // Generate base_truck.csv
        ExportToCSVTruck truckCsv = new ExportToCSVTruck();
        truckCsv.start(trucks);
    }
}
