package packageStation.zones;

import physicals.Truck;

import java.util.ArrayList;

public class WaitingZone {
    private final ArrayList<Truck> places;

    public WaitingZone() {
        this.places = new ArrayList<>();
    }

    public void addTruck(Truck truck) {
        places.add(truck);
    }

    public void removeTruck(Truck truck) {
        places.remove(truck);
    }

    public Truck getTruck(int i) {
        return places.get(i);
    }

    public int getPlaces() {
        return places.size();
    }
}
