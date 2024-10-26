package user_access.entities;

import ectimel.aggregates.AggregateRoot;
import ectimel.exceptions.NullException;
import lombok.experimental.SuperBuilder;
import user_access.value_objects.*;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@SuperBuilder
@Getter
@Entity
@Table(name = "users")
public class User extends AggregateRoot<UserId>
{

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", unique = true, nullable = false))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password", nullable = false))
    private Password password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "country", nullable = false))
    private Country country;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "born_date", nullable = false))
    private BornDate bornDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<DeviceFingerprint> deviceFingerprints;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    protected User()
    {
        // only for hibernate
    }

    @Override
    public void validate()
    {

    }

    public void changePassword(Password newPassword)
    {
        if (newPassword == null) throw new NullException("Password can not be null.");
        this.password = newPassword;
    }

    public boolean isFingerprintKnew(Fingerprint fingerprint)
    {
        var foundDevices = deviceFingerprints.stream()
                .filter(deviceFingerprint -> deviceFingerprint.validateFingerprint(fingerprint))
                .toList();
        
        return !foundDevices.isEmpty();
    }

}
