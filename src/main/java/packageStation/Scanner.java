package packageStation;

import algortihms.boyer_moore.BoyerMooreSubscriber;
import algortihms.rabin_karp.RabinKarpSubscriber;
import physicals.Package;
import packageStation.ControlUnit;
import packageStation.command.SearchAlgorithm;
import main_configuration.Configuration;

public class Scanner {
    private final BoyerMooreSubscriber boyerMooreSubscriber = new BoyerMooreSubscriber();
    private final RabinKarpSubscriber rabinKarpSubscriber = new RabinKarpSubscriber();
    private SearchAlgorithm searchAlgorithm = SearchAlgorithm.BM;
    private ControlUnit controlUnit;
    private Package currentPackage;


    public Scanner(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    public void search(Package p) {
        currentPackage = p;
        String string = p.getContent();
        if (SearchAlgorithm.BM.equals(searchAlgorithm)) {
            if (boyerMooreSubscriber.search(string, Configuration.instance.searchPattern))
                controlUnit.addDangerousPackage(p);
        } else {
            if (rabinKarpSubscriber.search(string, Configuration.instance.searchPattern))
                controlUnit.addDangerousPackage(p);
        }
    }

    public void unloadComponents() {
        //unload Components hier fehlt vllt noch was
        currentPackage = null;
    }

    public void changeSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }
}