
import java.util.List;

public interface ITeamBuilderFrontend {
    /**
     * Run the main application command loop
     */
    public void runCommandLoop();
    
    /**
     * Print out a list of players
     *
     * @param players the players to print
     */
    public void printPlayers(List<IPlayer> players);

    /**
     * Print out a team with some average statistics
     *
     * @param team the team to print out
     */
    public void printTeam(ITeam team);
}
