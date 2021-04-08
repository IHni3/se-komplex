package generate;

import main_configuration.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box {

    private String id;
    private final List<Level> lvl = new ArrayList<>(Configuration.instance.numberOfBoxLevels);
    private int lvlCount = 0;
    private int packageCount = 0;

    public Box() {
        generateBox();
    }

    private void generateBox() {
        for (var i = 0; i < Configuration.instance.numberOfBoxLevels; i++)
            lvl.add(new Level());
    }

    public void fillLvl(Package p) {
        if (packageCount < Configuration.instance.numberOfPackagesInBox) {
            if (packageCount < Configuration.instance.numberOfPackagesInBox / 2) {
                lvl.get(lvlCount).fillLeft(p);
            } else lvl.get(lvlCount).fillRight(p);
            packageCount++;
        } else {
            packageCount = 0;
            lvlCount++;
            fillLvl(p);
        }
    }

    public void generateID() {
        // generate random ID
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;                 // 5 chars.
        Random random = new Random();
        id = random.ints(leftLimit, rightLimit + 1)
                // leave out Unicode chars and Capitalized Letters.
                .filter(i -> (i <= 57 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String getId() {
        return id;
    }

    public Package getPackage(int ind) {
        int l = ind / 8;
        return lvl.get(l).getPackage(ind % 8);
    }

    public void setID(String setID) {
        this.id = setID;
    }

    public String getPackageID() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            result.append("|").append(getPackage(i).getId());
        }
        return result.toString();
    }

    public void removeAll() {
        lvlCount = 0;
        packageCount = 0;

        lvl.clear();
        generateBox();
    }
}
