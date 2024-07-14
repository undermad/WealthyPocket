package ectimel.entities;

import ectimel.aggregates.AggregateRoot;
import ectimel.exceptions.NullException;
import ectimel.exceptions.RoleAlreadyGrantedException;
import ectimel.exceptions.UnauthorizedException;
import ectimel.value_objects.Email;
import ectimel.value_objects.Password;
import ectimel.value_objects.UserId;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.Set;

@Getter
@Entity
@Table(name = "users", schema = "user_access")
public class User extends AggregateRoot<UserId> {

    @Embedded
    @AttributeOverride(name = "value", column = @Column (name = "email", unique = true, nullable = false))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column (name = "password", nullable = false))
    private Password password;
    
    @ManyToMany()
    @JoinTable(name = "user_roles",
            schema = "user_access",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    protected User() {
        // only for hibernate
    }

    public User(UserId userId, Email email, Password password, Set<Role> roles) {
        super(userId);
        this.email = email;
        this.password = password;
        this.roles = roles;
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
