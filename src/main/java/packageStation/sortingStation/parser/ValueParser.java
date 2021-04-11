package packageStation.sortingStation.parser;

import physicals.Package;
import packageStation.ControlUnit;

public class ValueParser extends Parser {
    public ValueParser(Parser successor) {
        this.setSuccessor(successor);
    }

    public void parse(Package p, ControlUnit controlUnit) {
        if (checkPackageType(p, "VALUE")) {
            controlUnit.valuePackage(p);
        } else {
            super.parse(p, controlUnit);
        }
    }
}
