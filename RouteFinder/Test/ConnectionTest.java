import org.junit.Test;
import tutorial_03_soen343.Connection;
import tutorial_03_soen343.Station;

import static org.junit.Assert.assertEquals;

/**
 * Unit Test for the connection class
 */
public class ConnectionTest {
    private Station station1 = new Station("Guy Concordia");
    private Station station2 = new Station("Peel");
    private Connection connection = new Connection(station1,station2,"Green Line");

    /**
     * Get station test.
     */
    @Test
    public void getStationTest(){
        assertEquals(station1,connection.getStation1());
        assertEquals(station2,connection.getStation2());
    }

    /**
     * Get line name test.
     */
    @Test
    public void getLineNameTest(){
        assertEquals("Green Line",connection.getLineName());
    }
}
