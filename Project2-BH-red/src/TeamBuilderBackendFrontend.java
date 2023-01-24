import java.util.ArrayList;
import java.util.List;

/**
 * This is a placeholder class for the frontend developer
 * @author Cole Johnstone
 *
 */
public class TeamBuilderBackendFrontend implements ITeamBuilderBackend{
	private TeamFrontend team;
	
	public TeamBuilderBackendFrontend() {
		team = new TeamFrontend();
	}
	/**
     * Register a player into the global pool of players
     *
     * @param player the player to register
     */
	@Override
    public void registerPlayer(IPlayer player) {}

    /**
     * Get players with a minimum batting average
     *
     * @param minimum the minimum batting average to filter by
     * @return the list of filtered players
     */
	@SuppressWarnings("null")
	@Override
    public List<IPlayer> searchByBattingAverage(double minimum){
		List<IPlayer> list = new ArrayList<IPlayer>();
    	Player1Frontend player1 = new Player1Frontend();
    	Player2Frontend player2 = new Player2Frontend();
    	list.add(player2);
    	list.add(player1);
    	return list;
    }

    /**
     * Get players with a minimum number of home runs
     *
     * @param minimum the minimum number of home runs to filter by
     * @return the list of filtered players
     */
	@Override
    public List<IPlayer> searchByHomeRuns(int minimum){
		List<IPlayer> list = new ArrayList<IPlayer>();
    	Player1Frontend player1 = new Player1Frontend();
    	Player2Frontend player2 = new Player2Frontend();
    	list.add(player2);
    	list.add(player1);
    	return list;
	}

    /**
     * Get players with a minimum sprint speed
     *
     * @param minimum the minimum sprint speed to filter by
     * @return the list of filtered players
     */
	@Override
    public List<IPlayer> searchBySprintSpeed(double minimum){
		List<IPlayer> list = new ArrayList<IPlayer>();
    	Player1Frontend player1 = new Player1Frontend();
    	Player2Frontend player2 = new Player2Frontend();
    	list.add(player1);
    	list.add(player2);
    	return list;
	}

    /**
     * Get players with a maximum age
     *
     * @param maximum the maximum age to filter by
     * @return the list of filtered players
     */
	@Override
    public List<IPlayer> searchByAge(int maximum){
		List<IPlayer> list = new ArrayList<IPlayer>();
    	Player1Frontend player1 = new Player1Frontend();
    	Player2Frontend player2 = new Player2Frontend();
    	list.add(player2);
    	list.add(player1);
    	return list;
	}

    /**
     * Get the user's current team of players
     *
     * @return the team of players
     */
	@Override
    public ITeam getTeam() {
    	return team;
	}

    /**
     * Add a player to the user's team
     *
     * @param player the player to add
     */
	@Override
    public void addPlayerToTeam(IPlayer player) {
		team.add(player);
	}

    /**
     * Remove a player from the user's team
     *
     * @param player the player to remove
     */
	@Override
    public void removePlayerFromTeam(IPlayer player) {
		team.remove(player);
	}
}
