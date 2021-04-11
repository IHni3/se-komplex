package packageStation.sortingStation.parser;

import physicals.Package;
import packageStation.ControlUnit;

public class NormalParser extends Parser {
    public void parse(Package p, ControlUnit controlUnit) {
        if (checkPackageType(p, "NORMAL")) {
            controlUnit.normalPackage(p);
        } else {
            super.parse(p, controlUnit);
        }
    }
}
