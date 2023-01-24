/**
 * Placeholder class for algorithm engineer tests
 * @author Cole Johnstone
 *
 */
public class Flight1AE implements IFlight{
	
	private double cost;
	
	public Flight1AE() {
		cost = 1.0;
	}
	/**
     * Fetches the starting airport the flight starts from
     * 
     * @return starting airport
     */
    public String getStart() {
    	return null;
    }

    /**
     * Fetches the destination airport the flight ends at
     * 
     * @return ending airport
     */
    public String getDestination() {
    	return null;
    }

    /**
     * Fetches the cost of the flight
     * 
     * @return cost of flight
     */
    public double getCost() {
    	return cost;
    }

    /**
     * Fetches the distance traveled by the flight
     * 
     * @return the distance traveled
     */
    public double getDistance() {
    	return 0.0;
    }
}
