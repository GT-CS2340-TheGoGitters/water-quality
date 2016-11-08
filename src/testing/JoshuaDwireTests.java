package testing;

import controller.HistoryGraphInputController;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

public class JoshuaDwireTests {
    HistoryGraphInputController controller;
    Account account;

    @Before
    public void setUp() throws Exception {
        WaterReportsHolder.clearAll();
        controller = new HistoryGraphInputController();
        account = new Account("John Smith", "jsmith", "jsmith", AccountType.USR);
    }

    @Test
    public void testQueryEmpty() {
        WaterPurityReport[] reports = controller.getReportsForQuery(
                toDate(LocalDate.now().minusYears(1)),
                toDate(LocalDate.now().plusYears(1)),
                new ReportLocation(0, 0),
                9000000
        );

        assertArrayEquals(
                "Querying an empty model should return no reports.",
                new WaterPurityReport[]{},
                reports
        );
    }

    @Test
    public void testQuerySourceOnly() {
        WaterReportsHolder.addReport(
                new WaterSourceReport(
                        account,
                        new ReportLocation(5, 5),
                        WaterType.BOTTLED,
                        WaterOverallCondition.POTABLE
                )
        );


        WaterPurityReport[] reports = controller.getReportsForQuery(
                toDate(LocalDate.now().minusYears(1)),
                toDate(LocalDate.now().plusYears(1)),
                new ReportLocation(0, 0),
                9000000
        );

        assertArrayEquals(
                "Querying a model with only source reports should return no reports.",
                new WaterPurityReport[]{},
                reports
        );
    }

    @Test
    public void testOutsideRadius() {
        WaterReportsHolder.addReport(
                new WaterPurityReport(
                        account,
                        new ReportLocation(10, 10),
                        WaterCondition.SAFE,
                        100,
                        100
                )
        );


        WaterPurityReport[] reports = controller.getReportsForQuery(
                toDate(LocalDate.now().minusYears(1)),
                toDate(LocalDate.now().plusYears(1)),
                new ReportLocation(0, 0),
                20
        );

        assertArrayEquals(
                "Querying a model with no reports inside radius should return no reports.",
                new WaterPurityReport[]{},
                reports
        );
    }

    @Test
    public void testBeforeDateRange() {
        WaterReportsHolder.addReport(
                new WaterPurityReport(
                        account,
                        new ReportLocation(10, 10),
                        WaterCondition.SAFE,
                        100,
                        100
                )
        );


        WaterPurityReport[] reports = controller.getReportsForQuery(
                toDate(LocalDate.now().plusYears(1)),
                toDate(LocalDate.now().plusYears(2)),
                new ReportLocation(0, 0),
                9000000
        );

        assertArrayEquals(
                "Querying a model with all reports before date range should return no reports.",
                new WaterPurityReport[]{},
                reports
        );
    }

    @Test
    public void testAfterDateRange() {
        WaterReportsHolder.addReport(
                new WaterPurityReport(
                        account,
                        new ReportLocation(10, 10),
                        WaterCondition.SAFE,
                        100,
                        100
                )
        );


        WaterPurityReport[] reports = controller.getReportsForQuery(
                toDate(LocalDate.now().minusYears(1)),
                toDate(LocalDate.now().minusMonths(2)),
                new ReportLocation(0, 0),
                9000000
        );

        assertArrayEquals(
                "Querying a model with all reports after date range should return no reports.",
                new WaterPurityReport[]{},
                reports
        );
    }

    @Test
    public void testMultipleReports() {
        WaterPurityReport reportOutside = new WaterPurityReport(
                account,
                new ReportLocation(10, 10),
                WaterCondition.SAFE,
                10,
                10
        );

        WaterPurityReport reportInside1 = new WaterPurityReport(
                account,
                new ReportLocation(5, 5),
                WaterCondition.SAFE,
                5,
                32
        );

        WaterPurityReport reportInside2 = new WaterPurityReport(
                account,
                new ReportLocation(5, 5),
                WaterCondition.SAFE,
                100,
                130
        );

        WaterReportsHolder.addReport(reportOutside);
        WaterReportsHolder.addReport(reportInside1);
        WaterReportsHolder.addReport(reportInside2);


        WaterPurityReport[] reports = controller.getReportsForQuery(
                toDate(LocalDate.now().minusYears(1)),
                toDate(LocalDate.now().plusYears(1)),
                new ReportLocation(4.9, 4.9),
                200
        );

        assertArrayEquals(
                "Should have received 2 reports.",
                new WaterPurityReport[]{reportInside1, reportInside2},
                reports
        );
    }


    private Date toDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}