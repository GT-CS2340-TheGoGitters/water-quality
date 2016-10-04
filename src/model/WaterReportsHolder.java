package model;

import java.util.HashMap;

/**
 * Created by Joshua on 9/29/16.
 */
public class WaterReportsHolder {
    private static HashMap<Integer, WaterReport> reportsList = new HashMap<>();

    public static void addReport(WaterReport report){
        reportsList.put(report.getReportNumber(), report);
    }

    public static WaterReport[] getReports(){
        return reportsList.values().toArray(new WaterReport[reportsList.size()]);
    }

}
