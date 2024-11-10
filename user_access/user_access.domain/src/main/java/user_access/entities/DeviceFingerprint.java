package user_access.entities;

import ectimel.aggregates.EntityObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import user_access.value_objects.*;

import java.sql.Timestamp;

@SuperBuilder
@Setter
@Entity
@Table(name = "devices_fingerprints")
@AllArgsConstructor
public class DeviceFingerprint extends EntityObject<DeviceFingerprintID> {

    protected DeviceFingerprint() {
        // only for hibernate
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "fingerprint", nullable = false))
    private Fingerprint fingerprint;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "ip_address", nullable = false))
    private IpAddress ipAddress;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_agent", nullable = false))
    private UserAgent userAgent;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "operating_system", nullable = false))
    private OperatingSystem operatingSystem;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "device", nullable = false))
    private Device device;

    @Column(name = "last_login", nullable = false)
    private Timestamp lastLogin;

    @Getter
    @Column(name = "verified", nullable = false)
    private boolean isVerified;


    public boolean validateFingerprint(Fingerprint fingerprint) {
        return this.fingerprint.equals(fingerprint);
    }

}
