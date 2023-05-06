package src;

public class CommonTypes<T> {

    // Function type
    String valueType;
    T value;

    public CommonTypes(String _valueType, T _value) {
        this.valueType = _valueType;
        this.value = _value;
    }
}
