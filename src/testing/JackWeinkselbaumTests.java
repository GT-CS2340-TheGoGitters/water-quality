package testing;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class JackWeinkselbaumTests {
    //Testing getValues() in WaterReportsHolder

    private HashMap<Integer, WaterReport> testAgainst;
    private WaterReport wr;
    private Account acct;
    private ReportLocation rl;

    @Before
    public void setUp() throws Exception {
        testAgainst = new HashMap<>();
        acct = new Account("Jack", "user", "pass", AccountType.USR);
        rl = new ReportLocation(12.4, 12.4);
        wr = new WaterReport(acct, rl);
    }

    @Test
    public void testAddReport() {
        ReportLocation rl1 = new ReportLocation(100, 100);
        WaterReport wr1 = new WaterReport(acct, rl1);
        testAgainst.put(2, wr);
        WaterReportsHolder.addReport(wr);
        testAgainst.put(3, wr1);
        WaterReportsHolder.addReport(wr1);
        assertEquals(testAgainst, WaterReportsHolder.getReportsList());
    }

    @Test
    public void clearAll() {
        WaterReportsHolder.clearAll();
        testAgainst = new HashMap<>();
        assertEquals(WaterReportsHolder.getReportsList(), testAgainst);
    }

    @Test
    public void testGetValuesAdding() {
        ReportLocation rl1 = new ReportLocation(100, 100);
        WaterReport wr1 = new WaterReport(acct, rl1);
        WaterReportsHolder.addReport(wr);
        WaterReportsHolder.addReport(wr1);
        testAgainst.put(2, wr);
        testAgainst.put(3, wr1);
        WaterReport[] vals = testAgainst.values().toArray(new WaterReport[WaterReportsHolder.getReportsList().size()]);
        assertArrayEquals(vals, WaterReportsHolder.getValues());
    }

    @Test
    public void testGetValuesAfterClear() {
        WaterReportsHolder.clearAll();
        testAgainst = new HashMap<>();
        WaterReport[] vals1 = testAgainst.values().toArray(new WaterReport[WaterReportsHolder.getReportsList().size()]);
        assertArrayEquals(vals1, WaterReportsHolder.getValues());

    }
}