package model;

import java.io.Serializable;

public enum WaterType implements Serializable{
    BOTTLED ("Bottled", "BOTTLED"),
    WELL ("Well", "WELL"),
    STREAM ("Stream", "STREAM"),
    LAKE ("Lake", "LAKE"),
    SPRING ("Spring", "SPRING"),
    OTHER ("Other", "OTHER");

    private final String text;

    private final String code;

    /**
     * Constructor for WaterType
     * @param text type of water
     * @param code code for the type of water
     */
    WaterType(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public String toString() {
        return text;
    }
}

