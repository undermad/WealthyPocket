package user_access.factories;

import user_access.entities.DeviceFingerprint;
import user_access.value_objects.DeviceFingerprintID;
import user_access.value_objects.Fingerprint;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class DeviceFingerprintFactory
{

    public static DeviceFingerprint createFingerprint(DeviceFingerprintData deviceFingerprintData)
    {
        var fingerprint = Fingerprint.createFingerprint(deviceFingerprintData.ipAddress(),
                deviceFingerprintData.userAgent(),
                deviceFingerprintData.operatingSystem(),
                deviceFingerprintData.device());

        return DeviceFingerprint.builder()
                .id(new DeviceFingerprintID(UUID.randomUUID()))
                .user(deviceFingerprintData.user())
                .fingerprint(fingerprint)
                .ipAddress(deviceFingerprintData.ipAddress())
                .userAgent(deviceFingerprintData.userAgent())
                .operatingSystem(deviceFingerprintData.operatingSystem())
                .device(deviceFingerprintData.device())
                .lastLogin(Timestamp.from(Instant.now()))
                .isVerified(false)
                .build();
    }

}
