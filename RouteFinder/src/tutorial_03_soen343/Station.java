package tutorial_03_soen343;

/**
 * The type Station.
 */
public class Station
{
    private String name;
    private double stationCost;

    /**
     * Instantiates a new Station.
     *
     * @param name the name
     */
    public Station(String name) {
        this.name = name;
        stationCost = 1;
    }

    /**
     * Gets the station name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof Station) {
            Station otherStation = (Station) obj;
            if (otherStation.getName().equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    /**
     * Get cost for the station double.
     *
     * @return the double
     */
    public double getStationCost(){
        return stationCost;
    }
}
