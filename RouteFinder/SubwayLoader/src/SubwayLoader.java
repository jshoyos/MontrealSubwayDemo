import java.io.File;
import java.io.*;

/**
 * The type Subway loader.
 */
public class SubwayLoader
{
    private Subway subway;

    /**
     * Instantiates a new Subway loader.
     */
    public SubwayLoader() {
        this.subway = new Subway();
    }

    /**
     * Load from file subway.
     *
     * @param subwayFile the subway file
     * @return the subway
     * @throws IOException the io exception
     */
    public Subway loadFromFile(File subwayFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(subwayFile));
        String lineName = reader.readLine();
        while ((lineName != null) && (lineName.length() > 0)) {
            loadLine(subway, reader, lineName);
            lineName = reader.readLine();
        }
        return subway;
    }

    //This method is used to load the stations and is called before a connection id added
    private void loadStations(Subway subway, String stationName1,String stationName2){
        subway.addStation(stationName1);
        subway.addStation(stationName2);
    }

    //This Method is used to load the lines of the subway. i.e green Line and its stations , blue line and its stations ....
    private void loadLine(Subway subway, BufferedReader reader, String lineName) throws IOException {
        String station1Name, station2Name;
        station1Name = reader.readLine();
        station2Name = reader.readLine();
        while ((station2Name != null) && (station2Name.length() > 0)) {
            loadStations(subway,station1Name,station2Name);
            subway.addConnection(station1Name, station2Name, lineName);
            station1Name = station2Name;
            station2Name = reader.readLine();
        }
    }
}
