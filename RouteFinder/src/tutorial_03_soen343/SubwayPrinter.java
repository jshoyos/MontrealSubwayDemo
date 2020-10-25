package tutorial_03_soen343;

import java.io.*;
import java.util.*;

/**
 * The type Subway printer.
 */
public class SubwayPrinter
{
    private PrintStream out;

    /**
     * Instantiates a new Subway printer.
     *
     * @param out the out
     */
    public SubwayPrinter(OutputStream out) {
        this.out = new PrintStream(out);
    }

    /**
     * Print directions.
     *
     * @param route the route
     */
    public void printDirections(List route) {
        try{
            OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(new File("Output.txt")));
            Connection connection = (Connection) route.get(0);
            String currentLine = connection.getLineName();
            String previousLine = currentLine;

            out.println("Start out at " + connection.getStation1().getName() + ".");
            os.write("Start out at " + connection.getStation1().getName() + ".\n");
            out.println("Get on the " + currentLine + " heading towards " + connection.getStation1().getName() + ".");
            os.write("Get on the " + currentLine + " heading towards " + connection.getStation1().getName() + ".\n");

            for (int i = 1; i < route.size(); i++) {
                connection = (Connection) route.get(i);
                currentLine = connection.getLineName();
                if (currentLine.equals(previousLine)) {
                    out.println("  Continue past  " + connection.getStation1().getName() + "...");
                    os.write("  Continue past  " + connection.getStation1().getName() + "...\n");
                } else {
                    out.println("When you get to " + connection.getStation1().getName() + ", get off the " + previousLine + ".");
                    os.write("When you get to " + connection.getStation1().getName() + ", get off the " + previousLine + ".\n");
                    out.println("Switch over to the " + currentLine + ", heading towards " + connection.getStation2().getName() + ".");
                    os.write("Switch over to the " + currentLine + ", heading towards " + connection.getStation2().getName() + ".\n");
                    previousLine = currentLine;
                }
            }
            out.println("Get off at " + connection.getStation2().getName() + " and enjoy yourself!");
            os.write("Get off at " + connection.getStation2().getName() + " and enjoy yourself!\n");
            printFare(os);
            os.close();
        }catch (IOException e){
            throw new RuntimeException("There was an error with the output file: "+e.getMessage());
        }
    }

    /**
     * Print total route fare.
     *
     * @param os the os
     * @throws IOException the io exception
     */
    private void printFare(OutputStreamWriter os) throws IOException {
        os.write("Total route price: " + Subway.getRouteCost());
    }
}
