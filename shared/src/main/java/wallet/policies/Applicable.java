package wallet.policies;

public interface Applicable<T> {
    Boolean isApplicable(T applicableData);
}
