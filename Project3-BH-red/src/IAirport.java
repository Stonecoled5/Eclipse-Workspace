
/**
 * Interface including methods necessary for an object representing an airport
 */
public interface IAirport {
    /**
     * Returns the city the airport is in
     * 
     * @return city of airport
     */
    public String getCity();

    /**
     * Returns the IATA code of the airport
     * 
     * @return IATA code
     */
    public String getIATACode();
}
