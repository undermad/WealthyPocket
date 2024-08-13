package ectimel.aggregates;

import jakarta.persistence.MappedSuperclass;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;

// SOME RULES TO BE FOLLOW

// 1. Reference other aggregates by id. <- use value object
// 2. Changes are committed & rolled back as whole <- keep aggregates small as we need to take out whole aggregate from the database to perform some changes.
// 3. Any changes to the aggregate need to be done from aggregate root.

// If id is equals, then aggregates are equal
// Hashcode of aggregates with same id are same even if other values are different.

@MappedSuperclass
public abstract class AggregateRoot<ID extends Serializable> extends EntityObject<ID> {

    public AggregateRoot() {
        // for hibernate only
    }
    
    public AggregateRoot(ID id) {
        super(id);
    }

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant updatedOn;
}
