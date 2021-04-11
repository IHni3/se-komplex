package packageStation.sortingStation.parser;

import packageStation.ControlUnit;
import physicals.Package;

public class Parser {
    private Parser successor;

    public void parse(Package p, ControlUnit controlUnit) {
        if (getSuccessor() != null) {
            getSuccessor().parse(p, controlUnit);
        } else {
            System.out.println("unable to find the correct parser for the package : " + p.getId());
        }
    }

    protected boolean checkPackageType(Package p, String type) {
        return (p == null) || (p.getType() == type);
    }

    public Parser getSuccessor() {
        return successor;
    }

    public void setSuccessor(Parser successor) {
        this.successor = successor;
    }
}
