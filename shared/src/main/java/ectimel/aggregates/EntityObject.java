package ectimel.aggregates;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


// See AggregateRoot for rules

@SuperBuilder
@Getter
@Setter
@MappedSuperclass
public abstract class EntityObject<ID extends Serializable> {
    
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
