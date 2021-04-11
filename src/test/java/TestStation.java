import packageStation.PackageSortingStation;
import physicals.AutonomousCar;

import main_configuration.Configuration;

import static org.junit.Assert.*;

public class TestStation {
    PackageSortingStation packageSortingStation;

    @org.junit.Before
    public void init()
    {
        packageSortingStation = new PackageSortingStation();
    }

    @org.junit.Test


    public void setup() {
        assertNotNull(packageSortingStation.getControlUnit());
        for (int i = 0; i < Configuration.instance.numberOfUnloadingZones; i++) {
            assertNotNull(packageSortingStation.getUnloadingZones()[i]);
            if(packageSortingStation.getUnloadingZones()[i] == null)
            {
                System.out.println("Missing Unloading Zone number: "+i);
            }
        }
        assertNotNull(packageSortingStation.getParkZone());
        assertEquals(packageSortingStation.getParkZone().getAutonomousCars().length, 5);
        for (int i = 0; i < Configuration.instance.numberOfAutonomousCars; i++) {
            assertNull(packageSortingStation.getParkZone().getAutonomousCars()[i]);
        }
        for (int i = 0; i < Configuration.instance.numberOfAutonomousCars; i++) {
            AutonomousCar autonomousCar = new AutonomousCar(packageSortingStation.getControlUnit().getEventBus());
            packageSortingStation.getParkZone().addCar(i, autonomousCar);
        }
        for (int i = 0; i < Configuration.instance.numberOfAutonomousCars; i++) {
            assertNotNull(packageSortingStation.getParkZone().getAutonomousCars()[i]);
            if(packageSortingStation.getParkZone().getAutonomousCars()[i]==null)
            {
                System.out.println("Missing car number"+i);
            }
        }
        assertNotNull(packageSortingStation.getSortingStation());

    }

    @org.junit.Test
    public void autonomousCarTest()
    {
        packageSortingStation.init();
        assertTrue(packageSortingStation.getSortingStation().getTemporaryStorage()[0].isEmpty());
        String truckId = packageSortingStation.getWaitingZone().getTruck(0).getTruckID();

        assertNotNull(packageSortingStation.getWaitingZone().getTruck(0));
        assertNull(packageSortingStation.getUnloadingZones()[0].getTruck());
        packageSortingStation.next();

        assertSame(packageSortingStation.getDispatchedTrucksList().get(0).getTruckID(), truckId);
        assertTrue(packageSortingStation.getDispatchedTrucksList().get(0).getTrailer().isEmpty());
        if(packageSortingStation.getDispatchedTrucksList().get(0).getTrailer().isEmpty()){
            System.out.println("Truck empty");
        }
        else
        {
            System.out.println("Truck is not empty");
        }

        assertNotNull(packageSortingStation.getSortingStation().getTemporaryStorage());
        if(packageSortingStation.getSortingStation().getTemporaryStorage()!=null)
        {
            System.out.println("Temporary Storage is not null");
        }
        else
        {
            System.out.println("Temporaray Storage is null");
        }
    }

}
