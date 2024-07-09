package ectimel.aggregates;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;


// See AggregateRoot for rules

@Getter
@MappedSuperclass
public abstract class EntityObject<ID extends ValueObject & Serializable> {
    
    @EmbeddedId
    private ID id;

    protected EntityObject() {
        // for hibernate only
    }
    
    public EntityObject(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityObject<?> that = (EntityObject<?>) o;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }
    
}
