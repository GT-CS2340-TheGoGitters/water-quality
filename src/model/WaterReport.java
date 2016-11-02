package model;

import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        /*try {
            created = new SimpleDateFormat("M/dd/yyyy").parse("05/01/2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
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

    public static void setReportNumber(int rN) {
        nextReportNumber = rN;
    }
}
