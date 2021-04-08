package packageStation.sortingStation.parser;

import generate.Package;
import packageStation.ControlUnit;

public class ValueParser extends Parser {
    public ValueParser(Parser successor) {
        this.setSuccessor(successor);
    }

    public void parse(Package p, ControlUnit cu) {
        if (canHandlePackage(p, "VALUE")) {
            cu.triggerValue(p);
        } else {
            super.parse(p, cu);
        }
    }
}
