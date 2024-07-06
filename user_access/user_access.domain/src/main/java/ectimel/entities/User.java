package ectimel.entities;

import ectimel.exceptions.NullException;
import ectimel.exceptions.PasswordNotValidException;
import ectimel.value_objets.Email;
import ectimel.value_objets.Password;
import ectimel.value_objets.UserId;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users", schema = "user_access")
public class User {

    @EmbeddedId
    private UserId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column (name = "email", unique = true, nullable = false))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column (name = "password", nullable = false))
    private Password password;


    protected User() {
        // only for hibernate
    }

    public boolean validatePassword(String password) {
        if (this.password.value().equals(password)) {
            return true;
        }
        throw new PasswordNotValidException();
    }
    
    public void changePassword(Password newPassword) {
        if(newPassword == null) {
            throw new NullException("Password can not be null.");
        }
        this.password = newPassword;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() == obj.getClass()) return false;
        return ((User) obj).id.id().equals(this.id.id());
    }
}
