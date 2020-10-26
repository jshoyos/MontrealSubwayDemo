import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit Test for the subway class.
 */
public class SubwayTest {
    private Subway subway = new Subway();
    private Subway loadedSubway;

    /**
     * Empty subway test.
     */
    @Test
    public void emptySubwayTest(){
        assertFalse(subway.hasConnection("Guy Concordia","Peel","Green Line"));
        assertFalse((subway.hasStation("Guy Concordia")));
        assertFalse(subway.hasStation("Peel"));
    }

    /**
     * Non empty subway test.
     */
    @Test
    public void nonEmptySubwayTest(){
        subway.addStation("Namur");
        subway.addStation("Plamodon");
        assertTrue(subway.hasStation("Namur"));
        assertTrue(subway.hasStation("PLAMODON"));
        assertFalse(subway.hasStation("PARC"));
        subway.addConnection("Namur","PLAMODON","Orange Line");
        assertTrue(subway.hasConnection("NAMUR","plamodon","orange LINE"));
        assertFalse(subway.hasConnection("Namur","Parc","blue Line"));
    }

    /**
     * Loaded subway test.
     */
    @Test
    public void loadedSubwayTest(){
        try{
           loadedSubway= new SubwayLoader().loadFromFile(new File("MontrealSubway.txt"));
            assertTrue(loadedSubway.hasConnection("Namur","PLAMOnDON","Orange LINE"));
            assertTrue(loadedSubway.hasConnection("VIAU","PIE Ix","green LINE"));
            assertFalse((loadedSubway.hasConnection("PArc","De la savane","blue line")));

            assertTrue(loadedSubway.hasStation("Mcgill"));
            assertTrue(loadedSubway.hasStation("atwater"));
            assertTrue(loadedSubway.hasStation("joliette"));
            assertFalse(loadedSubway.hasStation("Purple Rain"));
            assertFalse(loadedSubway.hasStation("snowup"));
        }
        catch (IOException e){
            fail("Error loading the file" + e.getMessage());
        }
    }

    /**
     * Get direction test.
     */
    @Test
    public void getDirectionTest(){
        try{
            //connections that will be used as the expected values
            Connection connection = new Connection(new Station("Lionel Groulx"),new Station("Atwater"),"Green Line");
            Connection connection2 = new Connection(new Station("Atwater"),new Station("Guy Concordia"),"Green Line");
            Connection connection3 = new Connection(new Station("Guy Concordia"),new Station("Peel"),"Green Line");
            List actual = new LinkedList();
            actual.add(connection);
            actual.add(connection2);
            actual.add(connection3);

            //subway used as the actual value
            loadedSubway= new SubwayLoader().loadFromFile(new File("MontrealSubway.txt"));
            List expected = loadedSubway.getDirections("Lionel Groulx","Peel");
            if(!actual.equals(expected)){
                fail("Not the same");
            }
        }
        catch (IOException e){
            fail("Error loading the file" + e.getMessage());
        }
    }
}
