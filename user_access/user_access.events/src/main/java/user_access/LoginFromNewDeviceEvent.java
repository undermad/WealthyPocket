package user_access;

import ectimel.message_broker.Event;
import lombok.Builder;

import java.util.UUID;

@Builder
public record LoginFromNewDeviceEvent(UUID id, UUID userId, String email) implements Event
{
    @Override
    public UUID getId()
    {
        return id;
    }
}
