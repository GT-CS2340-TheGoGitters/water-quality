package model;

/**
 * Created by Joshua on 10/4/16.
 */
public enum WaterOverallCondition {
    WASTE ("Waste", "WASTE"),
    TREATABLE_CLEAR ("Treatable-Clear", "TREATABLE_CLEAR"),
    TREATABLE_MUDDY ("Treatable-Muddy", "TREATABLE_MUDDY"),
    POTABLE ("Potable", "POTABLE");

    private String text;

    private String code;

    private WaterOverallCondition(String text, String code) {
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
