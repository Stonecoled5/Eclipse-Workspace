/**
 * A player on a MLB team
 *
 * Comparable implementation should compare based on batting average
 */
public interface IPlayer extends Comparable<IPlayer> {
    /**
     * Get the player's first name
     *
     * @return the player's first name
     */
    public String getFirstName();

    /**
     * Get the player's last name
     *
     * @return the player's last name
     */

    public String getLastName();

    /**
     * Get the player's age
     *
     * @return the player's age
     */
    public int getAge();

    /**
     * Get the total number of home runs from the player
     *
     * @return the player's total home runs
     */
    public int getHomeRuns();

    /**
     * Get the player's batting average
     *
     * @return the player's batting average
     */
    public double getBattingAverage();

    /**
     * Get the player's sprint speed
     *
     * @return the player's sprint speed
     */
    public double getSprintSpeed();
}
