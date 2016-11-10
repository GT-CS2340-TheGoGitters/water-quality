package model;

import java.io.Serializable;
import java.util.Date;


public class WaterReport implements Serializable{
    private static int nextReportNumber = 1;

    private final Date created;
    private final int reportNumber;
    private final Account creator;
    private final ReportLocation location;

    /**
     * Constructor for WaterReport
     * @param creator Account that's submitting report
     * @param location location where report is
     */
    public WaterReport(Account creator, ReportLocation location){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != WaterReport.class) {
            return false;
        }

        WaterReport checking = (WaterReport) o;

        if (checking.getCreator().equals(this.getCreator())
                && checking.getLocation().equals(this.getLocation())) {
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return creator.getName() + " " + Double.toString
                (location.getLatitude()) + " "
                + Double.toString(location.getLatitude());
    }
}
