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

    public WaterSourceReport(Account creator, ReportLocation location, WaterType waterType, WaterOverallCondition waterOverallCondition) {
        super(creator, location);
        this.waterType = waterType;
        this.waterOverallCondition = waterOverallCondition;
    }
}
