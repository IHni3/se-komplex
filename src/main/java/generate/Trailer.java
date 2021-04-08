package generate;

public class Trailer {

    private Pallet[] left = new Pallet[5];
    private Pallet[] right = new Pallet[5];
    private int palletCounter;
    private boolean empty;

    public void storePallet(Pallet p) {
        if (palletCounter < 5) {
            left[palletCounter] = p;
        } else {
            right[palletCounter - 5] = p;
        }
        palletCounter++;
        empty = false;
    }

    public int getLeftPalletID(int pos) {
        return left[pos].getID();
    }

    public int getRightPalletID(int pos) {
        return right[pos].getID();
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
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }
}
