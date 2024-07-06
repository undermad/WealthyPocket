package ectimel.value_objets;

import ectimel.exceptions.NullIdException;

import java.io.Serializable;
import java.util.UUID;

public record UserId(UUID id) implements Serializable {
    public UserId {
        if(id == null) throw new NullIdException(this.getClass());
    }
    
    public UserId() {
        this(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        return ((UserId) obj).id().equals(this.id);
    }
}
