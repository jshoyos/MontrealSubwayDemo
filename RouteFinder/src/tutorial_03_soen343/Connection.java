package tutorial_03_soen343;

import java.util.Objects;

public class Connection
{
    private Station station1, station2;
    private String lineName;
    
    public Connection(Station station1, Station station2, String lineName)
    {
        this.station1 = station1;
        this.station2 = station2;
        this.lineName = lineName;
    }

    public Station getStation1()
    {
        return station1;
    }

    public Station getStation2()
    {
        return station2;
    }

    public String getLineName()
    {
        return lineName;
    }
    
    public String toString()
    {
        return "[" + station1.getName() + ", " + station2.getName() + ", " + lineName + "]";
    }

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
