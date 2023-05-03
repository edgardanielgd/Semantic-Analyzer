package src;

public class CommonTypes<T> {
    String valueType;
    T value;

    public CommonTypes(String valueType, T value) {
        this.valueType = valueType;
        this.value = value;
    }
}
