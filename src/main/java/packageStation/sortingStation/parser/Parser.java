package packageStation.sortingStation.parser;

import generate.Package;
import packageStation.ControlUnit;

public class Parser {
    private Parser successor;

    public void parse(Package p, ControlUnit cu) {
        if (getSuccessor() != null) {
            getSuccessor().parse(p, cu);
        } else {
            System.out.println("unable to find the correct parser for the package : " + p.getId());
        }
    }

    protected boolean canHandlePackage(Package p, String type) {
        return (p == null) || (p.getType() == type);
    }

    public Parser getSuccessor() {
        return successor;
    }

    public void setSuccessor(Parser successor) {
        this.successor = successor;
    }
}
