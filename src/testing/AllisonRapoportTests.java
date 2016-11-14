package testing;

import model.Account;
import model.AccountType;
import model.ReportLocation;
import model.WaterReport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Allison on 11/13/16.
 * Tests for equals in WaterReport.java
 */
public class AllisonRapoportTests {

    private Account account;
    private WaterReport report1;
    private ReportLocation loc;
    private WaterReport report2;

    @Before
    public void setUp() throws Exception {
        account = new Account("Allison", "arapoport3", "password",
                AccountType.USR);
        loc = new ReportLocation(100, 200);
        report1 = new WaterReport(account, loc);
        report2 = new WaterReport(account, loc);
    }

    @Test
    public void testNull() {
        assertEquals(report1.equals(null), false);
    }

    @Test
    public void testSelf() {
        assertEquals(report1.equals(report1), true);
    }

    @Test
    public void testNotReport() {
        assertEquals(report1.equals(account), false);
    }

    @Test
    public void testEqual() {
        assertEquals(report1.equals(report2), true);
    }

}
