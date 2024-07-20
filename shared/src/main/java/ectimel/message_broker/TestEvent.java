package ectimel.message_broker;

public class TestEvent extends Event {
    
    private String message;

    public TestEvent(String message) {
        this.message = message;
    }
    
    public void printMessage() {
        System.out.println(message);
    }
}
