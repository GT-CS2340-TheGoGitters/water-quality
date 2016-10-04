package model;

/**
 * Created by Joshua on 10/4/16.
 */
public class WaterPurityReport extends WaterReport {
    private WaterCondition waterCondition;
    private int virusPPM;
    private int contaminantPPM;

    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    public int getVirusPPM() {
        return virusPPM;
    }

    public int getContaminantPPM() {
        return contaminantPPM;
    }

    public WaterPurityReport(Account creator, ReportLocation location, WaterCondition waterCondition, int virusPPM, int contaminantPPM) {
        super(creator, location);
        this.waterCondition = waterCondition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;

    }
}
