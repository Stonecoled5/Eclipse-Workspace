/**
 * Interface includes necessary methods for an object representing a single
 * flight between two airports 
 */ 
public interface IFlight {
    /**
     * Fetches the starting airport the flight starts from
     * 
     * @return starting airport
     */
    public String getStart();

    /**
     * Fetches the destination airport the flight ends at
     * 
     * @return ending airport
     */
    public String getDestination();

    /**
     * Fetches the cost of the flight
     * 
     * @return cost of flight
     */
    public double getCost();

    /**
     * Fetches the distance traveled by the flight
     * 
     * @return the distance traveled
     */
    public double getDistance();
}
