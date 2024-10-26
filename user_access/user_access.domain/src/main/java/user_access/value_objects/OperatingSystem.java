package user_access.value_objects;

import ectimel.exceptions.NullException;

public record OperatingSystem(String value)
{
    public OperatingSystem
    {
        if (value == null) throw new NullException("Operating system can not be null");
    }
}
