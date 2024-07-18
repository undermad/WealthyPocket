package ectimel;

import ectimel.message_broker.Event;

import java.util.UUID;

public class UserRegisteredEvent extends Event {
    private UUID userId;
    private String email;
}
