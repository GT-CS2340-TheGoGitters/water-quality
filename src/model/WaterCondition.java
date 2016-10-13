package model;

/**
 * Created by Joshua on 10/4/16.
 */
public enum WaterCondition {
    SAFE ("Safe", "SAFE"),
    TREATABLE ("Treatable", "TREATABLE"),
    UNSAFE ("Unsafe", "UNSAFE");

    private String text;

    private String code;

    private WaterCondition(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return text;
    }
}
