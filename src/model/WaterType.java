package model;

/**
 * Created by Joshua on 10/4/16.
 */
public enum WaterType {
    BOTTLED ("Bottled", "BOTTLED"),
    WELL ("Well", "WELL"),
    STREAM ("Stream", "STREAM"),
    LAKE ("Lake", "LAKE"),
    SPRING ("Spring", "SPRING"),
    OTHER ("Other", "OTHER");

    private String text;

    private String code;

    private WaterType(String text, String code) {
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
