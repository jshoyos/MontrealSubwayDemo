import org.junit.Test;
import tutorial_03_soen343.Station;

import static org.junit.Assert.*;

/**
 * Unit test for the station class
 */
public class StationTest {
    private Station station = new Station("Guy Concordia");
    private Station station2 = new Station("GUY CONCORDIA");
    private Station station3 = new Station("Guy");

    /**
     * Test the getName method from station class
     */
    @Test
    public void getNameTest(){
        assertEquals("Guy Concordia",station.getName());
    }

    /**
     * Test the getCostTest method from station class
     */
    @Test
    public void getCostTest(){
        assertEquals(1,station.getStationCost(),0);
        assertNotEquals(4,station.getStationCost(),0);
    }

    /**
     * Equals test.
     */
    @Test
    public void equalsTest(){
        assertTrue(station.equals(station2));
        assertFalse(station.equals(station3));
    }
}
