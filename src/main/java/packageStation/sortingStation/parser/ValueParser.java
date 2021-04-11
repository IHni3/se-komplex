package packageStation.sortingStation.parser;

import packageStation.ControlUnit;
import physicals.Package;

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
