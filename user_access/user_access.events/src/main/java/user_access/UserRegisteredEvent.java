package user_access;

import ectimel.message_broker.Event;

import java.util.UUID;

public record UserRegisteredEvent(UUID userId, String email) implements Event {
}
