package model;

import java.io.Serializable;
import java.util.logging.Logger;

public class WaterPurityReport extends WaterReport implements Serializable{
    private WaterCondition waterCondition;
    private int virusPPM;
    private int contaminantPPM;
    private static Logger LOGGER = Logger.getLogger("WaterReport");


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
     * @param creator Account that's submitting report
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

//    public void saveToText(PrintWriter pw) {
//        LOGGER.setLevel(Level.FINEST);
//        LOGGER.entering("WaterReport", "saveToText");
//        pw.println(this.getClass() + "," + this.getCreator() + "," + this.getLocation(), +"," + this.getCreated() + "," + this.getReportNumber(), "," + this.waterCondition + "," + this.virusPPM + "," + this.contaminantPPM);
//        LOGGER.exiting("WaterReport", "saveToText");
//    }


}
