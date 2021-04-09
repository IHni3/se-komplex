package generate;

public class BoxLevel {

    private final Package[] leftSide = new Package[4];
    private final Package[] rightSide = new Package[4];
    private int indexLeftSide = 0, indexRightSide = 0;


    public void fillLeft(Package p) {
        leftSide[indexLeftSide] = p;
        indexLeftSide++;
    }

    public void fillRight(Package p) {
        rightSide[indexRightSide] = p;
        indexRightSide++;
    }

    public Package getPackage(int i) {
        if (i < 4) {
            return leftSide[i];
        }
        else {
            i = i - 4;
            return rightSide[i];
        }
    }
}
