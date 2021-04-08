import packageStation.PackageSortingStation;
import vehicles.AutonomousCar;

import static org.junit.Assert.assertTrue;

public class TestStation {
    PackageSortingStation packageSortingStation;

    @org.junit.Test


    public void setup() {

        //PackageSortingStation
        packageSortingStation = new PackageSortingStation();
        assertTrue(packageSortingStation != null);

        //ControlUnit
        assertTrue(packageSortingStation.getControlUnit() != null);

        //7 unloaing Zones
        for (int i = 0; i < packageSortingStation.getUnloadingZones().length; i++) {
            assertTrue(packageSortingStation.getUnloadingZones()[i] != null);
        }

        //Parkzone
        assertTrue(packageSortingStation.getParkZone() != null);
        //Array for Cars has 5 empty Spaces
        assertTrue(packageSortingStation.getParkZone().getAutonomousCars().length == 5);
        for (int i = 0; i < packageSortingStation.getParkZone().getAutonomousCars().length; i++) {
            assertTrue(packageSortingStation.getParkZone().getAutonomousCars()[i] == null);
        }

        //fill Parkzone with 5 Cars
        for (int i = 0; i < packageSortingStation.getParkZone().getAutonomousCars().length; i++) {
            AutonomousCar car = new AutonomousCar(packageSortingStation.getControlUnit().getEventBus());
            assertTrue(car != null);
            packageSortingStation.getParkZone().addCar(i, car);
        }
        //Parkzone has 5 Cars
        for (int i = 0; i < packageSortingStation.getParkZone().getAutonomousCars().length; i++) {
            assertTrue(packageSortingStation.getParkZone().getAutonomousCars()[i] != null);
        }

        //SortingStation
        assertTrue(packageSortingStation.getSortingStation() != null);


        packageSortingStation.init();
        assertTrue(packageSortingStation.getSortingStation().getTemporaryStorage()[0].isEmpty());
        String truckId = packageSortingStation.getWaitingZone().getTruck(0).getTruckID();

        assertTrue(packageSortingStation.getWaitingZone().getTruck(0) != null);
        assertTrue(packageSortingStation.getUnloadingZones()[0].getTruck() == null);
        packageSortingStation.next();

        assertTrue(packageSortingStation.getDispatchedTrucks().get(0).getTruckID() == truckId);
        assertTrue(packageSortingStation.getDispatchedTrucks().get(0).getTrailer().isEmpty());

        assertTrue(packageSortingStation.getSortingStation().getTemporaryStorage() != null);
        //System.out.println("NEIIIIIIIIIIIIIIIIIIIIIIIIIN "+ packageSortingStation.getSortingStation().getTemporaryStorage()[0].getPallet(0));
        //assertTrue(packageSortingStation.getSortingStation().getTemporaryStorage()[0].isEmpty()==true);


    }

}
