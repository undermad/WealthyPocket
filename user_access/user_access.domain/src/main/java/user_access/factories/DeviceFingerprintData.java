package user_access.factories;

import lombok.Builder;
import user_access.entities.User;
import user_access.value_objects.Device;
import user_access.value_objects.IpAddress;
import user_access.value_objects.OperatingSystem;
import user_access.value_objects.UserAgent;

@Builder
public record DeviceFingerprintData(
        User user,
        IpAddress ipAddress,
        UserAgent userAgent,
        OperatingSystem operatingSystem,
        Device device)
{
    
    
    
    
}
