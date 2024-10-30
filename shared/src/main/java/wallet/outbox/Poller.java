package wallet.outbox;

import java.lang.reflect.InvocationTargetException;

public interface Poller {
    
    void poll() throws InvocationTargetException, IllegalAccessException;
}
