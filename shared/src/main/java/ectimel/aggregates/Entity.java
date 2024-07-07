package ectimel.aggregates;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.io.Serializable;


// See AggregateRoot for rules

@Getter
@MappedSuperclass
public abstract class Entity<ID extends ValueObject & Serializable> {
    
    @EmbeddedId
    private ID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> that = (Entity<?>) o;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }
    
}
