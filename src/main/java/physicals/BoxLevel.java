package physicals;

import main_configuration.Configuration;

public class BoxLevel {

    private final Package[] leftSide = new Package[Configuration.instance.numberOfPackagesInBox / Configuration.instance.numberOfBoxLevels / 2];
    private final Package[] rightSide = new Package[Configuration.instance.numberOfPackagesInBox / Configuration.instance.numberOfBoxLevels / 2];
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
        if (i < Configuration.instance.numberOfPackagesInBox / Configuration.instance.numberOfBoxLevels / 2) {
            return leftSide[i];
        } else {
            i = i - (Configuration.instance.numberOfPackagesInBox / Configuration.instance.numberOfBoxLevels / 2);
            return rightSide[i];
        }
    }
}
