package co.com.sofka.util;

public enum ResourceCases {
    SINGLE_USER("/users"),
    UPDATE_USER("/users/2"),
    TWO_SECONDS(""),
    THREE_SECONDS("");

    private final String value;

    ResourceCases(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

