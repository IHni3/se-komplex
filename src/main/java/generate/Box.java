package generate;

import main_configuration.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box {

    private String boxID;
    private final List<BoxLevel> lvlList = new ArrayList<>(Configuration.instance.numberOfBoxLevels);
    private int lvlCount = 0;
    private int packageCount = 0;

    public Box() {
        generateBox();
        generateBoxID();
    }

    private void generateBox() {
        for (int i = 0; i < Configuration.instance.numberOfBoxLevels; i++) {
            lvlList.add(new BoxLevel());
        }
    }

    public void fillBox(Package p) {
        int numberOfPackagesInLevel = Configuration.instance.numberOfPackagesInBox/Configuration.instance.numberOfBoxLevels;
        if (packageCount < numberOfPackagesInLevel) {
            if (packageCount < numberOfPackagesInLevel / 2) {
                lvlList.get(lvlCount).fillLeft(p);
            } else lvlList.get(lvlCount).fillRight(p);
            packageCount++;
        } else {
            packageCount = 0;
            lvlCount++;
            fillBox(p);
        }
    }

    private void generateBoxID() {
        // generate random ID
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;                 // 5 chars.
        Random random = new Random();
        boxID = random.ints(leftLimit, rightLimit + 1)
                // leave out Unicode chars and Capitalized Letters.
                .filter(i -> (i <= 57 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String getBoxID() {
        return boxID;
    }

    public Package getPackage(int ind) {
        int l = ind / 8;
        return lvlList.get(l).getPackage(ind % 8);
    }

    public void setBoxID(String setID) {
        this.boxID = setID;
    }

    public String getPackageID() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Configuration.instance.numberOfPackagesInBox; i++) {
            result.append("|").append(getPackage(i).getId());
        }
        return result.toString();
    }

    public void emptyBox() {
        lvlCount = 0;
        packageCount = 0;

        lvlList.clear();
        generateBox();
    }
}
