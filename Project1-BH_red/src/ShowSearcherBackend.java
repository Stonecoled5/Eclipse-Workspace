import java.util.ArrayList;
import java.util.List;

public class ShowSearcherBackend implements IShowSearcherBackend {
        private IHashTableSortedSets titleTable;
        private IHashTableSortedSets yearTable;
        private ArrayList<String> providers;

        public ShowSearcherBackend() {
                titleTable = (IHashTableSortedSets) new HashTableSortedSets();
                yearTable = (IHashTableSortedSets) new HashTableSortedSets();
                providers = new ArrayList<String>();
        }

        //public void add(KeyType key, ValueType value)
        public void addShow(IShow show) { // adds show to backend database
                String title = show.getTitle();
                title = title.replaceAll(":|!||;|.|\\?|,|\\/","");
                title = title.toLowerCase();
                for (String word : title.split(" ")) {
                        titleTable.add(word, show);     
                }
                yearTable.add(show.getYear(), show);    
        }
public int getNumberOfShows() { // retrieve number of shows in database
                return titleTable.size();
        }
        // set the desired provider filters before calling either search method:
        // (all providers are included in search results by default)
        public void setProviderFilter(String provider, boolean filter) {
                if (filter) {
                    providers.add(provider);
                } else {
                    providers.remove(provider);
                }
        }

        public boolean getProviderFilter(String provider) {
                if (providers.contains(provider)) { 
                        return true; 
                } else {
                        return false;
                }
        }

        public void toggleProviderFilter(String provider) {
                if (providers.contains(provider)) { 
                        providers.remove(provider);
                } else {
                        providers.add(provider);
                }
        }

        // these methods can be used to look-up shows by title word or year
        // the results are filtered according to the provider filters set above
        public List<IShow> searchByTitleWord(String word) {
                return (ArrayList) titleTable.get(word.toLowerCase());
        }
        public List<IShow> searchByYear(int year) {
                return (ArrayList) yearTable.get(year);
        }
    

}