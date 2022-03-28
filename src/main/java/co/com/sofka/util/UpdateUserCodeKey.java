package co.com.sofka.util;

public enum UpdateUserCodeKey {
    NAME_CODE("[nameCode]"),
    JOB_CODE("[jobCode]");

    private final String value;

    UpdateUserCodeKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
