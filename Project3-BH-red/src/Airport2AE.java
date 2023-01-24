/**
 * Placeholder class for tests
 * @author Cole Johnstone
 *
 */
public class Airport2AE implements IAirport{
private String city;
	public Airport2AE() {city = "Las Vegas";}
	@Override
	public String getCity() {
		// TODO Auto-generated method stub
		return city;
	}

	@Override
	public String getIATACode() {
		// TODO Auto-generated method stub
		return null;
	}
}
