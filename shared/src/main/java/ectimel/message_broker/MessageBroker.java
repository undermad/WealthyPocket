package ectimel.message_broker;

public interface MessageBroker {
    
    void publish(Event event);
    
    void subscribe(Class<? extends Event> event, Subscriber subscriber);
    
}
