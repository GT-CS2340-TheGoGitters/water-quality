package model;

/**
 * Created by Joshua on 10/4/16.
 */
public class WaterSourceReport extends WaterReport {

    private WaterType waterType;
    private WaterOverallCondition waterOverallCondition;

    public WaterType getWaterType() {
        return waterType;
    }

    public WaterOverallCondition getWaterOverallCondition() {
        return waterOverallCondition;
    }

    /**
     * Constructor for WaterReport
     * @param creator Acccount that's submitting report
     * @param location location where report is
     * @param waterType the type of te water
     * @param waterOverallCondition the condition of the water
     */
    public WaterSourceReport(Account creator, ReportLocation location, WaterType waterType, WaterOverallCondition waterOverallCondition) {
        super(creator, location);
        this.waterType = waterType;
        this.waterOverallCondition = waterOverallCondition;
    }
}
