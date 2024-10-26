package user_access.factories;

import user_access.entities.DeviceFingerprint;
import user_access.value_objects.Fingerprint;

public class DeviceFingerprintFactory
{

    public static DeviceFingerprint createFingerprint(DeviceFingerprintData deviceFingerprintData)
    {
        var fingerprint = Fingerprint.createFingerprint(deviceFingerprintData.ipAddress(),
                deviceFingerprintData.userAgent(),
                deviceFingerprintData.operatingSystem(),
                deviceFingerprintData.device());

        return DeviceFingerprint.builder()
                .user(deviceFingerprintData.user())
                .fingerprint(fingerprint)
                .ipAddress(deviceFingerprintData.ipAddress())
                .userAgent(deviceFingerprintData.userAgent())
                .operatingSystem(deviceFingerprintData.operatingSystem())
                .device(deviceFingerprintData.device())
                .build();
    }

}
