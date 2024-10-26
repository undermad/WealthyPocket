package user_access.value_objects;

import ectimel.exceptions.NullException;

public record Device(String value)
{
    public Device
    {
        if (value == null) throw new NullException("Device can no be null");
    }
}
