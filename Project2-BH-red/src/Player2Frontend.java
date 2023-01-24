
public class Player2Frontend implements IPlayer{
	private String FirstName;
	private String LastName;
	private int age;
	private int HR;
	private double BA;
	private double SS;
	
	public Player2Frontend() {
		FirstName = "Bob";
		LastName = "Vance";
		age = 32;
		HR = 60;
		BA = 300;
		SS = 25;
	}
	/**
     * Get the player's first name
     *
     * @return the player's first name
     */
    public String getFirstName() {return FirstName;}

    /**
     * Get the player's last name
     *
     * @return the player's last name
     */

    public String getLastName() {return LastName;}

    /**
     * Get the player's age
     *
     * @return the player's age
     */
    public int getAge() {return age;}

    /**
     * Get the total number of home runs from the player
     *
     * @return the player's total home runs
     */
    public int getHomeRuns() {return HR;}

    /**
     * Get the player's batting average
     *
     * @return the player's batting average
     */
    public double getBattingAverage() {return BA;}

    /**
     * Get the player's sprint speed
     *
     * @return the player's sprint speed
     */
    public double getSprintSpeed() {return SS;}

	@Override
	public int compareTo(IPlayer o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
