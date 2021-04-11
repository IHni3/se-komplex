package physicals;

import main_configuration.Configuration;

public class Trailer {

    private Pallet[] left = new Pallet[Configuration.instance.numberOfPalletsOnTrailer / 2];
    private Pallet[] right = new Pallet[Configuration.instance.numberOfPalletsOnTrailer / 2];
    private int palletCounter;
    private boolean isEmpty;

    public void loadTruck(Pallet pallet) {
        if (palletCounter < Configuration.instance.numberOfPalletsOnTrailer / 2) {
            left[palletCounter] = pallet;
        } else {
            right[palletCounter - (Configuration.instance.numberOfPalletsOnTrailer / 2)] = pallet;
        }
        palletCounter++;
        isEmpty = false;
    }

    public int getLeftPalletID(int pos) {
        return left[pos].getPalletID();
    }

    public int getRightPalletID(int pos) {
        return right[pos].getPalletID();
    }

    public Pallet[] getLeftPallets() {
        return left;
    }

    public Pallet[] getRightPallets() {
        return right;
    }

    public void emptyTrailer() {
        left = new Pallet[Configuration.instance.numberOfPalletsOnTrailer / 2];
        right = new Pallet[Configuration.instance.numberOfPalletsOnTrailer / 2];
        palletCounter = 0;
        isEmpty = true;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
