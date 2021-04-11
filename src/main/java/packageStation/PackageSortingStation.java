package packageStation;

import main_configuration.Configuration;
import packageStation.command.SearchAlgorithm;
import packageStation.sortingStation.SortingStation;
import packageStation.terminal.Terminal;
import packageStation.zones.ParkingZone;
import packageStation.zones.UnloadingZone;
import packageStation.zones.WaitingZone;
import physicals.Package;
import physicals.*;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PackageSortingStation {
    private final int[] numberOfPackagesGroupedByType = new int[3];
    private final UnloadingZone[] unloadingZones = new UnloadingZone[Configuration.instance.numberOfUnloadingZones];
    private final ArrayList<Truck> dispatchedTrucksList = new ArrayList<>();
    private final AutonomousCar[] autonomousCars = new AutonomousCar[Configuration.instance.numberOfAutonomousCars];
    private SortingStation sortingStation;
    private ControlUnit controlUnit;
    private ParkingZone parkingZone = new ParkingZone();
    private WaitingZone waitingZone = new WaitingZone();
    private List<String[]> truckAttributeList = new ArrayList<>();
    private List<String[]> palletAttributeList = new ArrayList<>();
    private List<String[]> boxAttributeList = new ArrayList<>();
    private List<String[]> packageAttributeList = new ArrayList<>();

    public PackageSortingStation() {
        controlUnit = new ControlUnit(this);
        sortingStation = new SortingStation(controlUnit);
        Terminal terminal = new Terminal(controlUnit);
        createUnloadingZones();
        createAutonomousCars();
    }

    private void createUnloadingZones() {
        for (int i = 0; i < Configuration.instance.numberOfUnloadingZones; i++) {
            UnloadingZone unloadingZone = new UnloadingZone(i, controlUnit);
            unloadingZones[i] = unloadingZone;
        }
    }

    private void createAutonomousCars() {
        for (int i = 0; i < Configuration.instance.numberOfAutonomousCars; i++) {
            AutonomousCar autonomousCar = new AutonomousCar(controlUnit.getEventBus());
            autonomousCar.addSubscriber(controlUnit);
            autonomousCars[i] = autonomousCar;
        }
    }

    public void init() {
        try (BufferedReader br = Files.newBufferedReader(Path.of(Configuration.instance.pathToTruckCSV))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                truckAttributeList.add(row.split(","));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try (BufferedReader br = Files.newBufferedReader(Path.of(Configuration.instance.pathToPalletCSV))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                palletAttributeList.add(row.split(","));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try (BufferedReader br = Files.newBufferedReader(Path.of(Configuration.instance.pathToBoxCSV))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                boxAttributeList.add(row.split(","));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try (BufferedReader br = Files.newBufferedReader(Path.of(Configuration.instance.pathToPackageCSV))) {
            String row = "";
            while ((row = br.readLine()) != null) {
                packageAttributeList.add(row.split(","));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        int boxCounter = 0;
        int packageCount = 0;
        for (int i = 0; i < Configuration.instance.numberOfTrucks; i++) {
            int j = i * 10;
            Truck truck = new Truck();
            truck.setTruckID(truckAttributeList.get(j)[0]);
            Trailer trailer = truck.getTrailer();
            for (int x = 0; x < Configuration.instance.numberOfPalletsOnTrailer; x++) {
                Pallet pallet = new Pallet(x + 1);
                for (int y = 0; y < 12; y++) {
                    Box box = new Box();
                    box.setBoxID(palletAttributeList.get(boxCounter)[Configuration.instance.numberOfPalletLevels]);
                    for (int z = 0; z < Configuration.instance.numberOfPackagesInBox; z++) {
                        Package p = new Package();
                        p.setID(boxAttributeList.get(boxCounter)[1].split("\\|")[z + 1]);
                        char[][][] pContent = new char[Configuration.instance.packageLength][Configuration.instance.packageWidth][Configuration.instance.packageHeight];
                        int contentCounter = 0;
                        for (int a = 0; a < Configuration.instance.packageLength; a++) {
                            for (int b = 0; b < Configuration.instance.packageWidth; b++) {
                                for (int c = 0; c < Configuration.instance.packageHeight; c++) {
                                    pContent[a][b][c] = packageAttributeList.get(packageCount)[1].charAt(contentCounter);
                                    contentCounter++;
                                }
                            }
                        }
                        p.setWeight(Float.parseFloat(packageAttributeList.get(packageCount)[4]));
                        p.setType(packageAttributeList.get(packageCount)[3]);
                        p.setZipCode(packageAttributeList.get(packageCount)[2]);
                        p.setContent(pContent);
                        box.fillBox(p);
                        packageCount++;
                    }
                    pallet.storeBox(box);
                    boxCounter++;
                }
                trailer.loadTruck(pallet);
            }
            waitingZone.addTruck(truck);
        }

    }

    public void next() {
        int random = new Random().nextInt(Configuration.instance.numberOfUnloadingZones);
        if (unloadingZones[random].getTruck() == null) {
            Truck truck = waitingZone.getTruck(0);
            waitingZone.removeTruck(truck);
            unloadingZones[random].addTruck(truck);
            unloadingZones[random].removeTruck();
        } else {
            next();
        }
    }

    public void showStatistics() {
        Report report = new Report.Builder()
                .date()
                .dispatchedTrucksCount(dispatchedTrucksList.size())
                .countPackagesGroupedByType(numberOfPackagesGroupedByType)
                .dangerousPackagesCount(controlUnit.getDangerousPackages().size())
                .build();
    }

    public void shutdown() {
        for (UnloadingZone unloadingZone : unloadingZones) {
            unloadingZone.turnOffSensor();
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
        sortingStation.changeAlgorithm(searchAlgorithm);
    }

    public void addDispatchedTruck(Truck truck) {
        dispatchedTrucksList.add(truck);
    }

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

    public void setNumberOfPackagesGroupedByType(int typeNumber) {
        numberOfPackagesGroupedByType[typeNumber]++;
    }

    public AutonomousCar getAutonomousCar(int number) {
        return autonomousCars[number];
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

    public ParkingZone getParkZone() {
        return parkingZone;
    }

    public void setParkZone(ParkingZone parkingZone) {
        this.parkingZone = parkingZone;
    }

    public List<String[]> getTruckAttributeList() {
        return truckAttributeList;
    }

    public void setTruckAttributeList(List<String[]> truckAttributeList) {
        this.truckAttributeList = truckAttributeList;
    }

    public List<String[]> getPalletAttributeList() {
        return palletAttributeList;
    }

    public void setPalletAttributeList(List<String[]> palletAttributeList) {
        this.palletAttributeList = palletAttributeList;
    }

    public List<String[]> getBoxAttributeList() {
        return boxAttributeList;
    }

    public void setBoxAttributeList(List<String[]> boxAttributeList) {
        this.boxAttributeList = boxAttributeList;
    }

    public List<String[]> getPackageAttributeList() {
        return packageAttributeList;
    }

    public void setPackageAttributeList(List<String[]> packageAttributeList) {
        this.packageAttributeList = packageAttributeList;
    }

    public ArrayList<Truck> getDispatchedTrucksList() {
        return dispatchedTrucksList;
    }
}
