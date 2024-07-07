package ectimel.aggregates;

import java.lang.reflect.Field;
import java.util.Objects;

// Value Objects
// 1. Measures, quantities, or describe something in the domain
// 2. They are immutable 
// 3. Are equal if values are equal. They don't contain id.
// 4. Side effect free functions

public abstract class ValueObject {

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Field[] fields = getClass().getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object thisFieldValue = field.get(this);
                Object otherFieldValue = field.get(obj);

                if (!Objects.equals(thisFieldValue, otherFieldValue)) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public int hashCode() {
        Field[] fields = getClass().getDeclaredFields();
        int result = 1;

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue = field.get(this);
                result = 31 * result + (fieldValue != null ? fieldValue.hashCode() : 0);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


}
