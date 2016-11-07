package model;

import java.io.Serializable;
import java.util.Date;


public class WaterReport implements Serializable{
    private static int nextReportNumber = 1;

    private Date created;
    private int reportNumber;
    private Account creator;
    private ReportLocation location;

    /**
     * Constructor for WaterReport
     * @param creator Account that's submitting report
     * @param location location where report is
     */
    WaterReport(Account creator, ReportLocation location){
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
     * @param creator Account that's submitting report
     * @param location location where report is
     */
    protected WaterReport(Account creator, ReportLocation location, Date created, int reportNumber){
        this.created = created;
        this.reportNumber = reportNumber;
        nextReportNumber++;
        this.creator = creator;
        this.location = location;
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

    public static void setNextReportNumber(int rN) {
        nextReportNumber = rN;
    }
}
