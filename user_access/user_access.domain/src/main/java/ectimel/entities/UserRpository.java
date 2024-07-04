package ectimel.entities;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRpository extends CrudRepository<User, UUID> {
    
    
}
