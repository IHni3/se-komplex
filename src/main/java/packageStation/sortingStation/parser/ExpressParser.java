package packageStation.sortingStation.parser;

import packageStation.ControlUnit;
import physicals.Package;

public class ExpressParser extends Parser {
    public ExpressParser(Parser successor) {
        this.setSuccessor(successor);
    }

    public void parse(Package p, ControlUnit controlUnit) {
        if (checkPackageType(p, "EXPRESS")) {
            controlUnit.expressPackage(p);
        } else {
            super.parse(p, controlUnit);
        }
    }
}
