package user_access.value_objects;

import ectimel.exceptions.NullException;

public record UserAgent(String value)
{
    public UserAgent
    {
        if (value == null) throw new NullException("User agent can not be null");
    }
}
