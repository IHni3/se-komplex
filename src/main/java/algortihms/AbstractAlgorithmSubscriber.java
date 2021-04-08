package algortihms;

import com.google.common.eventbus.Subscribe;
import events.Subscriber;

import java.lang.reflect.Method;

public abstract class AbstractAlgorithmSubscriber extends Subscriber {
    @Subscribe
    public boolean search(String string, String pattern) {
        boolean result = false;
        try {
            Method onMethod = getPort().getClass().getDeclaredMethod("search", String.class, String.class);
            result = (boolean) onMethod.invoke(getPort(), string, pattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected abstract Object getPort();
}
