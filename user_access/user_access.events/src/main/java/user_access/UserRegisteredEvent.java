package user_access;

import ectimel.message_broker.Event;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record UserRegisteredEvent(UUID id,
                                  UUID userId,
                                  String email,
                                  LocalDate bornDate,
                                  String country) implements Event {

    @Override
    public UUID getId() {
        return id;
    }
    
}
