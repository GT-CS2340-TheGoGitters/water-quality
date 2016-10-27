package model;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joshua on 10/4/16.
 */
public class WaterReport implements Serializable{
    private static int nextReportNumber = 1;

    private Date created;
    private int reportNumber;
    private Account creator;
    private ReportLocation location;
    private static Logger LOGGER = Logger.getLogger("WaterReport");

    /**
     * Constructor for WaterReport
     * @param creator Acccount that's submitting report
     * @param location location where report is
     */
    protected WaterReport(Account creator, ReportLocation location){
        created = new Date();
        reportNumber = ++nextReportNumber;
        this.creator = creator;
        this.location = location;
    }

    /**
     * Constructor for WaterReport
     * @param creator Acccount that's submitting report
     * @param location location where report is
     */
    protected WaterReport(Account creator, ReportLocation location, Date created, int reportNumber){
        this.created = created;
        this.reportNumber = reportNumber;
        nextReportNumber++;
        this.creator = creator;
        this.location = location;
    }

    public static int getNextReportNumber() {
        return nextReportNumber;
    }

    public Date getCreated() {
        return created;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public Account getCreator() {
        return creator;
    }

    public ReportLocation getLocation() {
        return location;
    }

//    public void saveToText(PrintWriter pw) {
//        LOGGER.setLevel(Level.FINEST);
//        LOGGER.entering("WaterReport", "saveToText");
//        if (this.getClass().equals(WaterPurityReport)) {
//            pw.println(this.getClass() + "," + creator + "," + location, +"," + created + "," + reportNumber);
//        } else if (this.getClass().equals(WaterSourceReport)) {
//            pw.println(this.getClass() + "," + creator + "," + location, +"," + created + "," + reportNumber);
//        }
//        LOGGER.exiting("WaterReport", "saveToText");
//    }
}
