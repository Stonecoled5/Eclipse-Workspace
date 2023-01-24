import java.nio.file.Path;
import java.util.List;

/**
 * A utility to load players from an XML file
 */
public interface IPlayerLoader {
    /**
     * Load players from an XML file
     *
     * @param path the path to the file containing player data
     * @return a list of players
     */
    public List<IPlayer> fromFile(Path path);
}