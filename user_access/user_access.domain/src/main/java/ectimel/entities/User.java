package ectimel.entities;

import ectimel.exceptions.PasswordNotValidException;
import ectimel.value_objets.Email;
import ectimel.value_objets.Password;
import ectimel.value_objets.UserId;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class User {
    
    @EmbeddedId
    private UserId userId;
    
    @Embedded
    private Email email;
    
    @Embedded
    private Password password;
    
    
    protected User() {
        // only for hibernate
    }
    
    public boolean validatePassword(String password) {
        if(!this.password.password().equals(password)) {
            throw new PasswordNotValidException();
        }
        return true;
    }
    
}
