package model;

import java.io.Serializable;

public enum WaterOverallCondition implements Serializable{
    WASTE ("Waste", "WASTE"),
    TREATABLE_CLEAR ("Treatable-Clear", "TREATABLE_CLEAR"),
    TREATABLE_MUDDY ("Treatable-Muddy", "TREATABLE_MUDDY"),
    POTABLE ("Potable", "POTABLE");

    private final String text;

    private final String code;

    /**
     * Constructor for WaterOverallCondition
     * @param text overall condition of water
     * @param code code for overall condition
     */
    WaterOverallCondition(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public String toString() {
        return text;
    }
}
