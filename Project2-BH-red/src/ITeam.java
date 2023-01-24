
import java.util.List;

/**
 * A team of players
 */
public interface ITeam extends List<IPlayer> {
    /**
     * Compute an average statistic of the team
     *
     * @param stat either "home runs", "batting average", or "sprint speed"
     * @return the averaged statistic
     */
    public double averageStat(String stat);
}
