package generate;

public class Trailer {

    private Pallet[] left = new Pallet[5];
    private Pallet[] right = new Pallet[5];
    private int palletCounter;
    private boolean isEmpty;

    public void loadTruck(Pallet pallet) {
        if (palletCounter < 5) {
            left[palletCounter] = pallet;
        } else {
            right[palletCounter - 5] = pallet;
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
        left = new Pallet[5];
        right = new Pallet[5];
        palletCounter = 0;
        isEmpty = true;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
