package user_access.value_objects;

import ectimel.exceptions.NullException;

import java.util.UUID;

public record Fingerprint(UUID value)
{
    public Fingerprint
    {
        if (value == null) throw new NullException("Fingerprint can not be null");
    }

    public static Fingerprint createFingerprint(IpAddress ipAddress, UserAgent userAgent, OperatingSystem operatingSystem, Device device)
    {
        var fingerprintString = ipAddress.value() + userAgent.value() + operatingSystem.value() + device.value(); 
        return new Fingerprint(UUID.nameUUIDFromBytes(fingerprintString.getBytes()));
    }


}
