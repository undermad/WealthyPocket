package user_access;

import ectimel.message_broker.Event;

import java.util.UUID;

public record UserRegisteredEvent(UUID id, UUID userId, String email) implements Event {
    
    @Override
    public UUID getId() {
        return id;
    }
}
