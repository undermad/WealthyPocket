package ectimel.policies;

public interface Applicable<T> {
    void isApplicable(T applicableData);
}
