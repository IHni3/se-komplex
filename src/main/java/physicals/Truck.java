package physicals;

import main_configuration.Configuration;

import java.util.Random;


public class Truck {

    private final Trailer trailer = new Trailer();
    private String truckID;

    public Truck() {
        // generate random ID

        Random random = new Random();
        truckID = random.ints(48, 91)
                // leave out Unicode chars.
                .filter(i -> (i <= 57 || i >= 65))
                .limit(Configuration.instance.lengthOfTruckID)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    public String getTruckID() {
        return truckID;
    }

    public void setTruckID(String setID) {
        this.truckID = setID;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public void storePallet(Pallet p) {
        trailer.loadTruck(p);
    }
}
