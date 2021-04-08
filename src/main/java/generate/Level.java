package generate;

public class Level {

    Package[] left = new Package[4];
    Package[] right = new Package[4];
    int indexLeft = 0;
    int indexRight = 0;

    public void fillLeft(Package p) {
        left[indexLeft] = p;
        indexLeft++;
    }

    public void fillRight(Package p) {
        right[indexRight] = p;
        indexRight++;
    }

    public Package getPackage(int ind) {
        if (ind < 4) return left[ind];
        else {
            ind = ind - 4;
            return right[ind];
        }
    }
}
