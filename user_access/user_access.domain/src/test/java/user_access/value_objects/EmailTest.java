package user_access.value_objects;


import wallet.exceptions.ValueNotValid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {
    
    @Test
    @DisplayName("Invalid Value")
    public void invalidValue() {
        assertThrows(ValueNotValid.class, () -> new Email(".email@gmail.com"));
        assertThrows(ValueNotValid.class, () -> new Email("email.@gmail.com"));
        assertThrows(ValueNotValid.class, () -> new Email("emailgmail.com"));
        assertThrows(ValueNotValid.class, () -> new Email("email@.gmail.com"));
        assertThrows(ValueNotValid.class, () -> new Email("email@gmail..com"));
        assertThrows(ValueNotValid.class, () -> new Email("email@gmail.com."));
        assertThrows(ValueNotValid.class, () -> new Email("ema@il@gmail.com"));
        assertThrows(ValueNotValid.class, () -> new Email(null));
    }
    
    @Test
    @DisplayName("Valid value")
    public void validValue() {
        Email email = new Email("test@gmail.com");
        Email email1 = new Email("test-test@gmail.com");
        Email email2 = new Email("test_test@gmail.com");
        Email email3 = new Email("test@gmail.co.uk");
        Email email4 = new Email("test.test@gmail.com");
        Email email5 = new Email("test^iing@gmail.com");

        assertEquals("test@gmail.com", email.value());
        assertEquals("test-test@gmail.com", email1.value());
        assertEquals("test_test@gmail.com", email2.value());
        assertEquals("test@gmail.co.uk", email3.value());
        assertEquals("test.test@gmail.com", email4.value());
        assertEquals("test^iing@gmail.com", email5.value());
    }
    
    @Test
    @DisplayName("Is equal")
    public void isEqual() {
        Email email = new Email("test@gmail.com");
        Email email2 = new Email("test@gmail.com");
        assertEquals(email, email2);
    }
    
    @Test
    @DisplayName("Is not equal")
    public void isNotEqual() {
        Email email = new Email("test@gmail.com");
        Email email1 = new Email("Test@gmail.com");
        assertNotEquals(email, email1);
    }
}
