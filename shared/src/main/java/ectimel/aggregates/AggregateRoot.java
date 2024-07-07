package ectimel.aggregates;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

// SOME RULES TO BE FOLLOW

// 1. Reference other aggregates by id. <- use value object
// 2. Changes are committed & rolled back as whole <- keep aggregates small as we need to take out whole aggregate from the database to perform some changes.
// 3. Any changes to the aggregate need to be done from aggregate root.

// If id is equals, then aggregates are equal
// Hashcode of aggregates with same id are same even if other values are different.

public abstract class AggregateRoot<ID extends ValueObject & Serializable> extends Entity<ID> {
    
}
