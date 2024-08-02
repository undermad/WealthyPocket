package ectimel.message_broker;


@FunctionalInterface
public interface Subscriber {
    
    void update(Event event);
}
