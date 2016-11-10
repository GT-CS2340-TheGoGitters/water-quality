package testing;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class JackWeinkselbaumTests {
    //Testing getValues() in WaterReportsHolder

    WaterReportsHolder wh;
    HashMap<Integer, WaterReport> testAgainst;
    WaterReport wr;
    Account acct;
    ReportLocation rl;

    @Before
    public void setUp() throws Exception {
        wh = new WaterReportsHolder();
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
        wh.addReport(wr);
        testAgainst.put(3, wr1);
        wh.addReport(wr1);
        assertEquals(testAgainst, wh.getReportsList());
    }

    @Test
    public void clearAll() {
        wh.clearAll();
        testAgainst = new HashMap<>();
        assertEquals(wh.getReportsList(), testAgainst);
    }

    @Test
    public void testGetValuesAdding() {
        ReportLocation rl1 = new ReportLocation(100, 100);
        WaterReport wr1 = new WaterReport(acct, rl1);
        wh.addReport(wr);
        wh.addReport(wr1);
        testAgainst.put(2, wr);
        testAgainst.put(3, wr1);
        WaterReport[] vals = testAgainst.values().toArray(new WaterReport[wh.getReportsList().size()]);
        assertArrayEquals(vals, wh.getValues());
    }

    @Test
    public void testGetValuesAfterClear() {
        wh.clearAll();
        testAgainst = new HashMap<>();
        WaterReport[] vals1 = testAgainst.values().toArray(new WaterReport[wh.getReportsList().size()]);
        assertArrayEquals(vals1, wh.getValues());

    }
}