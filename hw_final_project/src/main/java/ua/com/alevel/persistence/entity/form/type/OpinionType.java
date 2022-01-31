package ua.com.alevel.persistence.entity.form.type;

public enum OpinionType {

    NEGATIVE(-1),
    NEUTRAL(0),
    POSITIVE(1);

    private final Integer value;

    OpinionType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
