package ectimel.entities;

import ectimel.value_objets.Email;
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
    

    protected User() {

    }

    public User(Email email) {
        userId = new UserId();
        this.email = email;
    }

    
}
