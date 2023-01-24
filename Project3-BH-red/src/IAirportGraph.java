import java.util.List;

/**
 * Interface includes methods necessary for an object representing a
 * graph of airports
 */
public interface IAirportGraph extends GraphADT<IAirport> {
    /**
     * Adds an edge to the graph representing a flight
     * Will create endpoints if they do not exist
     * 
     * @param source starting airport
     * @param target destination airport
     * @param flight to add
     * @return true if successful, false otherwise
     */
    public boolean insertEdge(IAirport source, IAirport target, IFlight flight);

    /**
     * Returns the series of edges making up the cheapest travel
     * path between two cities
     * 
     * @param start city
     * @param end city
     * @return list of flights making up route
     */
    public List<IFlight> cheapestFlight(IAirport start, IAirport end);
}
