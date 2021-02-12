package enums;

public enum SpecialValue {
    T(10),
    J(11),
    Q(12),
    K(13),
    A(14);

    public final int value;

    SpecialValue(int value) {
        this.value = value;
    }
}
