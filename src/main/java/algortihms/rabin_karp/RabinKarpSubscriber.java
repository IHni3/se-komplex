package algortihms.rabin_karp;

import algortihms.AbstractAlgorithmSubscriber;

public class RabinKarpSubscriber extends AbstractAlgorithmSubscriber {

    private final Object port;

    public RabinKarpSubscriber() {
        this.port = RabinKarpFactory.build();
    }

    @Override
    protected Object getPort() {
        return port;
    }

}
