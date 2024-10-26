package user_access.value_objects;

import ectimel.exceptions.NullException;

public record IpAddress(String value)
{
    public IpAddress
    {
        if(value == null) throw new NullException("Ip Address can not be null");    
    }
}
