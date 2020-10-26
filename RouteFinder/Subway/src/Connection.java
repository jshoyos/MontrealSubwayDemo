/**
 * The type Connection.
 */
public class Connection
{
    private Station station1, station2;
    private String lineName;

    /**
     * Instantiates a new Connection.
     *
     * @param station1 the station 1
     * @param station2 the station 2
     * @param lineName the line name
     */
    public Connection(Station station1, Station station2, String lineName)
    {
        this.station1 = station1;
        this.station2 = station2;
        this.lineName = lineName;
    }

    /**
     * Gets station 1.
     *
     * @return the station 1
     */
    public Station getStation1()
    {
        return station1;
    }

    /**
     * Gets station 2.
     *
     * @return the station 2
     */
    public Station getStation2()
    {
        return station2;
    }

    /**
     * Gets line name.
     *
     * @return the line name
     */
    public String getLineName()
    {
        return lineName;
    }
    
    public String toString()
    {
        return "[" + station1.getName() + ", " + station2.getName() + ", " + lineName + "]";
    }

    /**
     * Checks if two connections are equal by comparing both stations and the line name
     * @param o object to compare with
     * @return boolean result of the compare
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection other = (Connection) o;
        return station1.equals(other.station1) &&
                station2.equals(other.station2) &&
                lineName.equalsIgnoreCase(other.lineName);
    }
}
