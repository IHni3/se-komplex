package packageStation;

import algortihms.boyer_moore.BoyerMooreSubscriber;
import algortihms.rabin_karp.RabinKarpSubscriber;
import main_configuration.Configuration;
import packageStation.command.SearchAlgorithm;
import physicals.Package;

public class Scanner {
    private final BoyerMooreSubscriber boyerMooreSubscriber = new BoyerMooreSubscriber();
    private final RabinKarpSubscriber rabinKarpSubscriber = new RabinKarpSubscriber();
    private SearchAlgorithm searchAlgorithm = SearchAlgorithm.BM;
    private final ControlUnit controlUnit;
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
        currentPackage = null;
    }

    public void changeSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }
}