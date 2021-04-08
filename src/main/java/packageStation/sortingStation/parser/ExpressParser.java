package packageStation.sortingStation.parser;

import generate.Package;
import packageStation.ControlUnit;

public class ExpressParser extends Parser {
    public ExpressParser(Parser successor) {
        this.setSuccessor(successor);
    }

    public void parse(Package p, ControlUnit cu) {
        if (canHandlePackage(p, "EXPRESS")) {
            cu.triggerExpress(p);
        } else {
            super.parse(p, cu);
        }
    }
}
