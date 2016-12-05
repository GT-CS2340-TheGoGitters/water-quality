package model.logging.security;

import model.Account;

public class ReportDeleteEntry extends SecurityLogEntry {
    protected Integer deletedReportNumber;

    public ReportDeleteEntry(Account actingAccount, int deletedReportNumber) {
        super(actingAccount);

        this.deletedReportNumber = deletedReportNumber;
    }

    public String getLabel() {
        return "Deleted Report";
    }

    public String getMessage() {
        return "Deleted report number " + deletedReportNumber.toString();
    }
}