package model;

import java.io.Serializable;

/**
 * Created by Joshua on 10/4/16.
 */
public enum WaterType implements Serializable{
    BOTTLED ("Bottled", "BOTTLED"),
    WELL ("Well", "WELL"),
    STREAM ("Stream", "STREAM"),
    LAKE ("Lake", "LAKE"),
    SPRING ("Spring", "SPRING"),
    OTHER ("Other", "OTHER");

    private String text;

    private String code;

    /**
     * Constructor for WaterType
     * @param text type of water
     * @param code code for the type of water
     */
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

