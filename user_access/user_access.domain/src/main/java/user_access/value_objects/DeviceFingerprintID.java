package user_access.value_objects;

import ectimel.exceptions.NullIdException;

import java.io.Serializable;
import java.util.UUID;

public record DeviceFingerprintID(UUID id) implements Serializable
{
    public DeviceFingerprintID
    {
        if (id == null) throw new NullIdException(DeviceFingerprintID.class);
    }
}
