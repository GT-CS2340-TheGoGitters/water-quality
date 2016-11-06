package model;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created by Joshua on 10/4/16.
 */
public class WaterSourceReport extends WaterReport implements Serializable{

    private WaterType waterType;
    private WaterOverallCondition waterOverallCondition;
    private static Logger LOGGER = Logger.getLogger("WaterReport");


    public WaterType getWaterType() {
        return waterType;
    }

    public WaterOverallCondition getWaterOverallCondition() {
        return waterOverallCondition;
    }

    /**
     * Constructor for WaterReport
     * @param creator Account that's submitting report
     * @param location location where report is
     * @param waterType the type of te water
     * @param waterOverallCondition the condition of the water
     */
    public WaterSourceReport(Account creator, ReportLocation location, WaterType waterType, WaterOverallCondition waterOverallCondition) {
        super(creator, location);
        this.waterType = waterType;
        this.waterOverallCondition = waterOverallCondition;
    }

//    public void saveToText(PrintWriter pw) {
//        LOGGER.setLevel(Level.FINEST);
//        LOGGER.entering("WaterReport", "saveToText");
//        pw.println(this.getClass() + "," + this.getCreator() + "," + this.getLocation(), +"," + this.getCreated() + "," + this.getReportNumber() + "," + this.waterType + "," + this.waterOverallCondition);
//        LOGGER.exiting("WaterReport", "saveToText");
//    }
}
