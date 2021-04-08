package algortihms.rabin_karp;

import algortihms.AbstractAlgorithmSubscriber;
import com.google.common.eventbus.Subscribe;
import events.Subscriber;

import java.lang.reflect.Method;

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
