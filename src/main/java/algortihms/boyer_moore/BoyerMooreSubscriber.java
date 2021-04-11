package algortihms.boyer_moore;

import algortihms.AbstractAlgorithmSubscriber;

public class BoyerMooreSubscriber extends AbstractAlgorithmSubscriber {

    private final Object port;

    public BoyerMooreSubscriber() {
        this.port = BoyerMooreFactory.build();
    }


    @Override
    protected Object getPort() {
        return port;
    }
}
