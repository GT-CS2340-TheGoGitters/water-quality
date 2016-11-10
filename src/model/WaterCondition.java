package model;

import java.io.Serializable;

public enum WaterCondition implements Serializable{
    SAFE ("Safe", "SAFE"),
    TREATABLE ("Treatable", "TREATABLE"),
    UNSAFE ("Unsafe", "UNSAFE");

    private final String text;

    private final String code;

    /**
     * Constructor for WaterCondition
     * @param text condition of water
     * @param code code for condition
     */
    WaterCondition(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public String toString() {
        return text;
    }
}
