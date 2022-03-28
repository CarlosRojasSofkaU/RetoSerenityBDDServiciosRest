package co.com.sofka.util;

public enum CreatePostCodeKey {
    TITLE_CODE("[titleCode]"),
    BODY_CODE("[bodyCode]"),
    USER_ID_CODE("[userIdCode]");

    private final String value;

    CreatePostCodeKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
