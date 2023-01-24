import java.util.List;

/**
 * The backend for the team builder application
 */
public interface ITeamBuilderBackend {
    /**
     * Register a player into the global pool of players
     *
     * @param player the player to register
     */
    public void registerPlayer(IPlayer player);

    /**
     * Get players with a minimum batting average
     *
     * @param minimum the minimum batting average to filter by
     * @return the list of filtered players
     */
    public List<IPlayer> searchByBattingAverage(double minimum);

    /**
     * Get players with a minimum number of home runs
     *
     * @param minimum the minimum number of home runs to filter by
     * @return the list of filtered players
     */
    public List<IPlayer> searchByHomeRuns(int minimum);

    /**
     * Get players with a minimum sprint speed
     *
     * @param minimum the minimum sprint speed to filter by
     * @return the list of filtered players
     */
    public List<IPlayer> searchBySprintSpeed(double minimum);

    /**
     * Get players with a maximum age
     *
     * @param maximum the maximum age to filter by
     * @return the list of filtered players
     */
    public List<IPlayer> searchByAge(int maximum);

    /**
     * Get the user's current team of players
     *
     * @return the team of players
     */
    public ITeam getTeam();

    /**
     * Add a player to the user's team
     *
     * @param player the player to add
     */
    public void addPlayerToTeam(IPlayer player);

    /**
     * Remove a player from the user's team
     *
     * @param player the player to remove
     */
    public void removePlayerFromTeam(IPlayer player);
}
