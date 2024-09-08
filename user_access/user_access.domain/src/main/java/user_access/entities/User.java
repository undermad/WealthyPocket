package user_access.entities;

import ectimel.aggregates.AggregateRoot;
import ectimel.exceptions.NullException;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import user_access.exceptions.RoleAlreadyGrantedException;
import user_access.exceptions.UnauthorizedException;
import user_access.value_objects.*;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.Set;

@SuperBuilder
@Getter
@Entity
@Table(name = "users")
public class User extends AggregateRoot<UserId> {

    @Embedded
    @AttributeOverride(name = "value", column = @Column (name = "email", unique = true, nullable = false))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column (name = "password", nullable = false))
    private Password password;
    
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "country", nullable = false))
    private Country country;
    
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "born_date", nullable = false))
    private BornDate bornDate;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    protected User() {
        // only for hibernate
    }

    

//    public User(UserId userId, Email email, Password password, Set<Role> roles) {
//        super(userId);
//        this.email = email;
//        this.password = password;
//        this.roles = roles;
//    }

    @Override
    public void validate() {

    }

    public boolean validatePassword(Password password) {
        if (this.password.equals(password)) return true;
        throw new UnauthorizedException();
    }
    
    public void changePassword(Password newPassword) {
        if(newPassword == null) throw new NullException("Password can not be null.");
        this.password = newPassword;
    }
    
    public void addRole(Role role) {
        if(role == null) throw new NullException("Role can not be null");
        if(roles.contains(role)) throw new RoleAlreadyGrantedException(role);
        roles.add(role);
    }
    
    public void removeRole(Role role) {
        if(role == null) throw new NullException("Role can not be null");
        roles.remove(role);
    }


}
