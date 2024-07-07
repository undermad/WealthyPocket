package ectimel.aggregates;

public abstract class AggregateRoot {
    
    // SOME RULES TO BE FOLLOW
    
    // 1. Reference other aggregates by id. <- use value object
    // 2. Changes are committed & rolled back as whole <- keep aggregates small as we need to take out whole aggreaget from the database to perform some changes.
    // 3. Any changes to the aggregate need to be done from aggregate root.
    
    // If id is equals, then aggregates are equal
    
    
    
    
}
