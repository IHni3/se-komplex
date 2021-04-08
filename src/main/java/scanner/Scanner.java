package scanner;

import algortihms.boyer_moore.BoyerMooreSubscriber;
import algortihms.rabin_karp.RabinKarpSubscriber;
import generate.Package;
import packageStation.ControlUnit;
import packageStation.command.SearchAlgorithm;

public class Scanner {
    BoyerMooreSubscriber boyerMooreSubscriber = new BoyerMooreSubscriber();
    RabinKarpSubscriber rabinKarpSubscriber = new RabinKarpSubscriber();
    SearchAlgorithm searchAlgorithm = SearchAlgorithm.BM;
    ControlUnit cu;
    Package currentPackage;


    public Scanner(ControlUnit cu) {
        this.cu = cu;
    }

    public void search(Package p) {
        currentPackage = p;
        String pattern = "exp|os!ve";
        String string = p.getContent();
        if (SearchAlgorithm.BM.equals(searchAlgorithm)) {
            if (boyerMooreSubscriber.search(string, pattern))
                cu.addDangerousPackage(p);
        } else {
            if (rabinKarpSubscriber.search(string, pattern))
                cu.addDangerousPackage(p);
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