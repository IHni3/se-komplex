package algortihms.boyer_moore;

import algortihms.AbstractAlgorithmSubscriber;
import com.google.common.eventbus.Subscribe;
import events.Subscriber;

import java.lang.reflect.Method;

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
