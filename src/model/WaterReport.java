package model;

import java.util.Date;

/**
 * Created by Joshua on 10/4/16.
 */
public class WaterReport {
    private static int nextReportNumber = 1;

    private Date created;
    private int reportNumber;
    private Account creator;
    private ReportLocation location;

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
}
