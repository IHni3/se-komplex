package packageStation.sortingStation.parser;

import generate.Package;
import packageStation.ControlUnit;

public class NormalParser extends Parser {
    public void parse(Package p, ControlUnit cu) {
        if (canHandlePackage(p, "NORMAL")) {
            cu.triggerNormal(p);
        } else {
            super.parse(p, cu);
        }
    }
}
