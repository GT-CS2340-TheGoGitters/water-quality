package model;

import java.io.File;
import java.util.HashMap;

public abstract class WaterReportsHolder {
    private static HashMap<Integer, WaterReport> reportsList = new HashMap<>();

    private static final File reportsFile = new File("ReportsSaveState.dat");

    public static void addReport(WaterReport report){

        reportsList.put(report.getReportNumber(), report);
    }

    public static WaterReport[] getValues(){
        return reportsList.values().toArray(new WaterReport[reportsList.size()]);
    }

    public static HashMap getReportsList() {
        return reportsList;
    }

    public static void saveReportsToBinary() {
        Persistence p = new Persistence(reportsList);
        p.saveToBinary(reportsFile);
    }

    public static void loadReportsFromBinary() {
        Persistence p = new Persistence(reportsList);
        p.loadFromBinary(reportsFile);
        reportsList = (HashMap) p.getBacking();

        int reportNum = 0;
        for (WaterReport value: reportsList.values()) {
            if (value.getReportNumber() > reportNum) {
                reportNum = value.getReportNumber();
            }
        }
        WaterReport.setNextReportNumber(reportNum);
    }

    public static void clearAll(){
        reportsList = new HashMap<>();
        WaterReport.setNextReportNumber(1);
    }
}
