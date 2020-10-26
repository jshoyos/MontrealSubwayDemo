import java.util.*;

/**
 * The type Subway. This class is the subway model and contains the logic for the subway
 */
public class Subway
{
    private List stations;
    private List connections;
    private Map network;
    private static double routeCost = 0;

    /**
     * Instantiates a new Subway.
     */
    public Subway() {
        this.stations = new LinkedList();
        this.connections = new LinkedList();
        this.network = new HashMap();
    }

    /**
     * Add station to the subway.
     *
     * @param stationName the station name
     */
    public void addStation(String stationName) {
        if (!this.hasStation(stationName)) {
            Station station = new Station(stationName);
            stations.add(station);
        }
    }

    /**
     * Has station boolean.
     *
     * @param stationName the station name
     * @return the boolean
     */
    public boolean hasStation(String stationName) {
        return stations.contains(new Station(stationName));
    }

    /**
     * Add connection between stations.
     *
     * @param station1Name the station 1 name
     * @param station2Name the station 2 name
     * @param lineName     the line name
     */
    public void addConnection(String station1Name, String station2Name, String lineName) {
    	if ((this.hasStation(station1Name)) && (this.hasStation(station2Name))) {
            Station station1 = new Station(station1Name);
            Station station2 = new Station(station2Name);
            Connection connection = new Connection(station1, station2, lineName);
            connections.add(connection);
            connections.add(new Connection(station2, station1, connection.getLineName()));
            
            addToNetwork(station1, station2);
            addToNetwork(station2, station1);
        }
        else
        {
            throw new RuntimeException("Invalid connection: [" + station1Name + ", " + station2Name + ", " + lineName + "]");
        }
    }

    //Private method only use to add the station to the map
    private void addToNetwork(Station station1, Station station2) {
        if (network.keySet().contains(station1)) {
            List connectingStations = (List) network.get(station1);
            if (!connectingStations.contains(station2)) {
                connectingStations.add(station2);
            }
        } else {
            List connectingStations = new LinkedList();
            connectingStations.add(station2);
            network.put(station1, connectingStations);
        }
    }

    /**
     * Gets directions.
     *
     * @param startStationName the start station name
     * @param endStationName   the end station name
     * @return the directions
     */
    public List getDirections(String startStationName, String endStationName) {
        routeCost = 0;
        if (!this.hasStation(startStationName) || !this.hasStation(endStationName))
        {
            throw new RuntimeException("Stations entered do not exist on this subway");
        }
        
        Station start = new Station(startStationName);
        Station end = new Station(endStationName);
        List route = new LinkedList();
        List reachableStations = new LinkedList();
        Map previousStations = new HashMap();
        List neighbors = (List)network.get(start);
        
        for (Iterator i = neighbors.iterator(); i.hasNext(); ) {
            Station station = (Station) i.next();
            if (station.equals(end)) {
                route.add(getConnection(start, end));
                return route;
            } else {
                reachableStations.add(station);
                previousStations.put(station, start);
            }
        }
        
        List nextStations = new LinkedList();
        nextStations.addAll(neighbors);
        Station currentStation = start;
        
        searchLoop:
        for (int i = 1; i < stations.size(); i++) {
            List tmpNextStations = new LinkedList();
            for (Iterator j = nextStations.iterator(); j.hasNext(); ) {
                Station station = (Station) j.next();
                reachableStations.add(station);
                currentStation = station;
                List currentNeighbors = (List) network.get(currentStation);
                for (Iterator k = currentNeighbors.iterator(); k.hasNext(); ) {
                    Station neighbor = (Station) k.next();
                    if (neighbor.equals(end)) {
                        reachableStations.add(neighbor);
                        previousStations.put(neighbor, currentStation);
                        break searchLoop;
                    } else if (!reachableStations.contains(neighbor)) {
                        reachableStations.add(neighbor);
                        tmpNextStations.add(neighbor);
                        previousStations.put(neighbor, currentStation);
                    }
                }
            }
            nextStations = tmpNextStations;
        }
        
        //We've found the path now!
        boolean keepLooping = true;
        Station keyStation = end;
        Station station;
        
        while (keepLooping) {
            station = (Station) previousStations.get(keyStation);
            route.add(0, getConnection(station, keyStation));
            if (start.equals(station)) {
                keepLooping = false;
            }
            keyStation = station;
        }
        calculateRouteCost(previousStations,start,end);
        return route;
    }
    
    private Connection getConnection(Station station1, Station station2) {
        for (Iterator i = connections.iterator(); i.hasNext(); ) {
            Connection connection = (Connection) i.next();
            Station one = connection.getStation1();
            Station two = connection.getStation2();
            if ((station1.equals(one)) && station2.equals(two)) {
                return connection;
            }
        }
        return null;
    }


    /**
     * Has connection boolean.
     *
     * @param station1Name the station 1 name
     * @param station2Name the station 2 name
     * @param lineName     the line name
     * @return the boolean
     */
    public boolean hasConnection(String station1Name, String station2Name, String lineName) {
        Station station1 = new Station(station1Name);
        Station station2 = new Station(station2Name);
        for (Iterator i = connections.iterator(); i.hasNext(); ) {
            Connection connection = (Connection) i.next();
            if (connection.getLineName().equalsIgnoreCase(lineName)) {
                if ((connection.getStation1().equals(station1)) &&
                    (connection.getStation2().equals(station2)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the price of the route
     * @param stations
     * @param start
     * @param end
     */
    private void calculateRouteCost(Map stations,Station start, Station end){
        boolean keepLooping = true;
        boolean includeLast = true;
        Station station = end;
        while(keepLooping){
            station = (Station) stations.get(end);
            if (start.equals(station)) {
                keepLooping = false;
            }
            routeCost += station.getStationCost();
            end = station;
        }
    }

    /**
     * Get route cost double.
     *
     * @return the double
     */
    public static double getRouteCost(){
        return routeCost;
    }
                
}
