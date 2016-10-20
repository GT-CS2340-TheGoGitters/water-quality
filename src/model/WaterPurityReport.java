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

    /**
     * Constructor for WaterReport
     * @param creator Acccount that's submitting report
     * @param location location where report is
     * @param virusPPM the virusPPM of the water
     * @param contaminantPPM the contaminantPPM of the water
     */
    public WaterPurityReport(Account creator, ReportLocation location, WaterCondition waterCondition, int virusPPM, int contaminantPPM) {
        super(creator, location);
        this.waterCondition = waterCondition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;

    }
}
