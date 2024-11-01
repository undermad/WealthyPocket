package wallet.validators;

public interface Validator<T> {
    boolean isValid(T value);
}
