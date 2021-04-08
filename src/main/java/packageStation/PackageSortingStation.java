package packageStation;

import generate.Package;
import generate.*;
import mainConfiguration.Configuration;
import packageStation.buildReport.Report;
import packageStation.command.SearchAlgorithm;
import packageStation.sortingStation.SortingStation;
import packageStation.terminal.Terminal;
import packageStation.zones.ParkZone;
import packageStation.zones.WaitingZone;
import packageStation.zones.unloadingZone.UnloadingZone;
import vehicles.AutonomousCar;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PackageSortingStation {
    private SortingStation sortingStation;
    private int numberOfPackagesGroupedByType[];
    private ControlUnit controlUnit;
    private ParkZone parkZone;
    private UnloadingZone[] unloadingZones;
    private WaitingZone waitingZone;
    private List<String[]> truckAttribute;
    private List<String[]> palletAttribute;
    private List<String[]> boxAttribute;
    private List<String[]> packageAttribute;
    private ArrayList<Truck> dispatchedTrucks;
    private AutonomousCar[] autonomousCars;

    public PackageSortingStation() {
        numberOfPackagesGroupedByType = new int[3];
        parkZone = new ParkZone();
        waitingZone = new WaitingZone();
        controlUnit = new ControlUnit(this);
        sortingStation = new SortingStation(controlUnit);
        Terminal terminal = new Terminal(controlUnit);
        truckAttribute = new ArrayList<>();
        palletAttribute = new ArrayList<>();
        boxAttribute = new ArrayList<>();
        packageAttribute = new ArrayList<>();
        unloadingZones = new UnloadingZone[7];
        fillUnloadingZones();
        dispatchedTrucks = new ArrayList<>();
        autonomousCars = new AutonomousCar[5];
        fillAutonomousCars();
    }

    private void fillUnloadingZones() {
        for (int i = 0; i < unloadingZones.length; i++) {
            UnloadingZone unloadingZone = new UnloadingZone(i, controlUnit);
            unloadingZones[i] = unloadingZone;
        }
    }

    private void fillAutonomousCars() {
        for (int i = 0; i < autonomousCars.length; i++) {
            AutonomousCar autonomousCar = new AutonomousCar(controlUnit.getEventBus());
            autonomousCar.addSubscriber(controlUnit);
            autonomousCars[i] = autonomousCar;
        }
    }

    public void init() {
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/main/java/CSV Daten/base_truck.csv"))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                truckAttribute.add(row.split(","));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/main/java/CSV Daten/base_pallet.csv"))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                palletAttribute.add(row.split(","));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/main/java/CSV Daten/base_box.csv"))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                boxAttribute.add(row.split(","));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/main/java/CSV Daten/base_package.csv"))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                packageAttribute.add(row.split(","));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        int boxCounter = 0;
        int packageCount = 0;
        for (int i = 0; i < Configuration.instance.numberOfLKWS; i++) {
            int j = i * 10;
            Truck truck = new Truck();
            truck.setTruckID(truckAttribute.get(j)[0]);
            Trailer trailer = truck.getTrailer();
            for (int x = 1; x <= 10; x++) {
                Pallet pallet = new Pallet(x);
                for (int y = 0; y < 12; y++) {
                    Box box = new Box();
                    box.setID(palletAttribute.get(boxCounter)[3]);
                    for (int z = 1; z < 41; z++) {
                        Package p = new Package();
                        p.setID(boxAttribute.get(boxCounter)[1].split("\\|")[z]);
                        char pContent[][][] = new char[25][10][10];
                        int contentCounter = 0;
                        for (int a = 0; a < 25; a++) {
                            for (int b = 0; b < 10; b++) {
                                for (int c = 0; c < 10; c++) {
                                    pContent[a][b][c] = packageAttribute.get(packageCount)[1].charAt(contentCounter);
                                    contentCounter++;
                                }
                            }
                        }
                        p.setWeight(Float.parseFloat(packageAttribute.get(packageCount)[4]));
                        p.setType(packageAttribute.get(packageCount)[3]);
                        p.setZip(packageAttribute.get(packageCount)[2]);
                        p.setContent(pContent);
                        box.fillLvl(p);
                        packageCount++;
                    }
                    pallet.storeBox(box);
                    boxCounter++;
                }
                trailer.storePallet(pallet);
            }
            waitingZone.addTruck(truck);
            System.out.println("Truck " + truck.getTruckID() + " in WaitingZone");
        }

    }

    public void next() {
        int i = new Random().nextInt(7);
        if (unloadingZones[i].getTruck() == null) {
            Truck truck = waitingZone.getTruck(0);
            waitingZone.removeTruck(truck);
            unloadingZones[i].addTruck(truck);
            unloadingZones[i].removeTruck();
            //dispatchedTrucks.add(truck);
        } else {
            next();
        }
    }

    public void showStatistics() {
        Report report = new Report.Builder()
                .date()
                .numberOfDispatchedLKW(dispatchedTrucks.size())
                .numberOfPackagesGroupedByType(numberOfPackagesGroupedByType)
                .numberOfDangerousPackages(controlUnit.getDangerousPackages().size())
                .build();
    }

    public void shutdown() {
        for (int i = 0; i < unloadingZones.length; i++) {
            unloadingZones[i].turnOffSensor();
        }
        sortingStation.unloadComponents();
    }

    public void lock() {
        sortingStation.setLocked(true);
    }

    public void unlock() {
        sortingStation.setLocked(false);
    }

    public void changeSearchingAlgorithm(SearchAlgorithm searchAlgorithm) {
        controlUnit.setSearchAlgorithm(searchAlgorithm);
        sortingStation.changeSearchAlgorithm(searchAlgorithm);
    }

    public void addDispatchedTruck(Truck truck) {
        dispatchedTrucks.add(truck);
    }

    //Überprüfung
    public UnloadingZone[] getUnloadingZones() {
        return unloadingZones;
    }

    public WaitingZone getWaitingZone() {
        return waitingZone;
    }

    public void setWaitingZone(WaitingZone waitingZone) {
        this.waitingZone = waitingZone;
    }

    public int[] getNumberOfPackagesGroupedByType() {
        return numberOfPackagesGroupedByType;
    }

    public void setNumberOfPackagesGroupedByType(int i) {
        numberOfPackagesGroupedByType[i]++;
    }

    public AutonomousCar getAutonomousCar(int i) {
        return autonomousCars[i];
    }

    public SortingStation getSortingStation() {
        return sortingStation;
    }

    public void setSortingStation(SortingStation sortingStation) {
        this.sortingStation = sortingStation;
    }

    public ControlUnit getControlUnit() {
        return controlUnit;
    }

    public void setControlUnit(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    public ParkZone getParkZone() {
        return parkZone;
    }

    public void setParkZone(ParkZone parkZone) {
        this.parkZone = parkZone;
    }

    public List<String[]> getTruckAttribute() {
        return truckAttribute;
    }

    public void setTruckAttribute(List<String[]> truckAttribute) {
        this.truckAttribute = truckAttribute;
    }

    public List<String[]> getPalletAttribute() {
        return palletAttribute;
    }

    public void setPalletAttribute(List<String[]> palletAttribute) {
        this.palletAttribute = palletAttribute;
    }

    public List<String[]> getBoxAttribute() {
        return boxAttribute;
    }

    public void setBoxAttribute(List<String[]> boxAttribute) {
        this.boxAttribute = boxAttribute;
    }

    public List<String[]> getPackageAttribute() {
        return packageAttribute;
    }

    public void setPackageAttribute(List<String[]> packageAttribute) {
        this.packageAttribute = packageAttribute;
    }

    public ArrayList<Truck> getDispatchedTrucks() {
        return dispatchedTrucks;
    }
}
