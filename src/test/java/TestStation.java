import packageStation.PackageSortingStation;
import vehicles.AutonomousCar;

import static org.junit.Assert.*;

public class TestStation {
    PackageSortingStation packageSortingStation;

    @org.junit.Test


    public void setup() {

        //PackageSortingStation
        packageSortingStation = new PackageSortingStation();

        //ControlUnit
        assertNotNull(packageSortingStation.getControlUnit());

        //7 unloaing Zones
        for (int i = 0; i < packageSortingStation.getUnloadingZones().length; i++) {
            assertNotNull(packageSortingStation.getUnloadingZones()[i]);
        }

        //Parkzone
        assertNotNull(packageSortingStation.getParkZone());
        //Array for Cars has 5 empty Spaces
        assertEquals(5, packageSortingStation.getParkZone().getAutonomousCars().length);
        for (int i = 0; i < packageSortingStation.getParkZone().getAutonomousCars().length; i++) {
            assertNull(packageSortingStation.getParkZone().getAutonomousCars()[i]);
        }

        //fill Parkzone with 5 Cars
        for (int i = 0; i < packageSortingStation.getParkZone().getAutonomousCars().length; i++) {
            AutonomousCar car = new AutonomousCar(packageSortingStation.getControlUnit().getEventBus());
            packageSortingStation.getParkZone().addCar(i, car);
        }
        //Parkzone has 5 Cars
        for (int i = 0; i < packageSortingStation.getParkZone().getAutonomousCars().length; i++) {
            assertNotNull(packageSortingStation.getParkZone().getAutonomousCars()[i]);
        }

        //SortingStation
        assertNotNull(packageSortingStation.getSortingStation());


        packageSortingStation.init();
        assertTrue(packageSortingStation.getSortingStation().getTemporaryStorage()[0].isEmpty());
        String truckId = packageSortingStation.getWaitingZone().getTruck(0).getTruckID();

        assertNotNull(packageSortingStation.getWaitingZone().getTruck(0));
        assertNull(packageSortingStation.getUnloadingZones()[0].getTruck());
        packageSortingStation.next();

        assertSame(packageSortingStation.getDispatchedTrucks().get(0).getTruckID(), truckId);
        assertTrue(packageSortingStation.getDispatchedTrucks().get(0).getTrailer().isEmpty());

        assertNotNull(packageSortingStation.getSortingStation().getTemporaryStorage());
        //System.out.println("NEIIIIIIIIIIIIIIIIIIIIIIIIIN "+ packageSortingStation.getSortingStation().getTemporaryStorage()[0].getPallet(0));
        //assertTrue(packageSortingStation.getSortingStation().getTemporaryStorage()[0].isEmpty()==true);


    }

}
