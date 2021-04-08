package generate;

import java.util.Random;


public class Truck {

    Trailer trailer = new Trailer();
    String id;

    public Truck() {
        // generate random ID
        int leftLimit = 48; // numeral '0'
        int rightLimit = 90; // letter 'Z'
        int targetStringLength = 4;                 // 6 chars.
        Random random = new Random();
        id = random.ints(leftLimit, rightLimit + 1)
                // leave out Unicode chars.
                .filter(i -> (i <= 57 || i >= 65))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    public String getTruckID() {
        return id;
    }

    public void setTruckID(String setID) {
        this.id = setID;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public void storePallet(Pallet p) {
        trailer.storePallet(p);
    }
}
