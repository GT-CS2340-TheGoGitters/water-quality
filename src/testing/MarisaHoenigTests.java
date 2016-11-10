package testing;

import model.Account;
import model.AccountType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * test for setUpEditPage() in EditAccountController.java
 */
public class MarisaHoenigTests {
    Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account("Marisa", "mhoenig3", "password",
                AccountType.USR);
    }

    @Test
    public void testEmailNull() {
        assertNull(account.getEmailAddress());
    }

    @Test
    public void testAddressNull() {
        assertNull(account.getHomeAddress());
    }

    @Test
    public void testTitleNull() {
        assertNull(account.getTitle());
    }

    @Test
    public void testEmailFilled() {
        account.setEmailAddress("mhoenig3@gatech.edu");
        assertEquals(account.getEmailAddress(), "mhoenig3@gatech.edu");
        assertNotNull(account.getEmailAddress());
    }

    @Test
    public void testAddressFilled() {
        account.setHomeAddress("Georgia Tech Station");
        assertEquals(account.getHomeAddress(), "Georgia Tech Station");
        assertNotNull(account.getHomeAddress());
    }

    @Test
    public void testAddressPartsFilled() {
        account.setHomeAddress("330662 Georgia Tech Station\nAtlanta,"
                + "GA\n30332");
        String[] addressComponents = account.getHomeAddress().split("\n");
        String addressOne = addressComponents[0];
        String[] cityState = addressComponents[1].split(",");
        String city = cityState[0];
        String state = cityState[1];
        String zip = addressComponents[2];
        assertEquals(addressOne, "330662 Georgia Tech Station");
        assertEquals(city, "Atlanta");
        assertEquals(state, "GA");
        assertEquals(zip, "30332");
        assertNotNull(account.getHomeAddress());
        assertEquals(account.getHomeAddress(), "330662 Georgia Tech Station"
                + "\nAtlanta,GA\n30332");
    }

    @Test
    public void testTitleFilled() {
        account.setTitle("Miss");
        assertEquals(account.getTitle(), "Miss");
        assertNotNull(account.getTitle());

    }

}
