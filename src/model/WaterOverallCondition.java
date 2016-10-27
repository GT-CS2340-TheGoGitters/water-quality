package model;

import java.io.Serializable;

/**
 * Created by Joshua on 10/4/16.
 */
public enum WaterOverallCondition implements Serializable{
    WASTE ("Waste", "WASTE"),
    TREATABLE_CLEAR ("Treatable-Clear", "TREATABLE_CLEAR"),
    TREATABLE_MUDDY ("Treatable-Muddy", "TREATABLE_MUDDY"),
    POTABLE ("Potable", "POTABLE");

    private String text;

    private String code;

    /**
     * Constructor for WaterOverallCondition
     * @param text overall condition of water
     * @param code code for overall condition
     */
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
