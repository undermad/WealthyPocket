package ectimel.validators;

public interface Validator<T> {
    boolean isValid(T value);
}
