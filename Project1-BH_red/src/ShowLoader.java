

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.Scanner;

/**
 * 
 * The following csv columns are used to load these show attributes:
 *   - Title: the complete title for a show
 *   - Year: the year that the show was first produced
 *   - Rotten Tomatoes: the review score (out of 100) for this show
 *   - Netflix: 1 = available on this service, other wise 0
 *   - Hulu: 1 = available on this service, other wise 0
 *   - Prime Video: 1 = available on this service, other wise 0
 *   - Disney+: 1 = available on this service, other wise 0
 */
public class ShowLoader implements IShowLoader {
	
	/**
     * This method loads the list of shows described within a CSV file.
     * @param filepath is relative to executable's working directory
     * @return a list of show objects that were read from specified file
     */
    public List<IShow> loadShows(String filepath) throws FileNotFoundException{
    	Path path = Paths.get(filepath);
    	if(!Files.exists(path)) throw new FileNotFoundException("This file path does not exist");
    	
    	//parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File(filepath));
        List<IShow> list = new ArrayList<IShow>();
        String[] headers = sc.nextLine().split(",");
        int title = -1;
        int year = -1;
        int rotTom = -1;
        int Netflix = -1;
        int Hulu = -1;
        int Prime = -1;
        int Disney = -1;
        for(int i = 0; i < headers.length; i++) {
        	if(headers[i] == null) continue;
        	if(headers[i].equals("Title")) title = i;
        	if(headers[i].equals("Year")) year = i;
        	if(headers[i].equals("Rotten Tomatoes")) rotTom = i;
        	if(headers[i].equals("Netflix")) Netflix = i;
        	if(headers[i].equals("Hulu")) Hulu = i;
        	if(headers[i].equals("Prime Video")) Prime = i;
        	if(headers[i].equals("Disney+")) Disney = i;
        }
        while (sc.hasNext()) { 
        	String nextLine = sc.nextLine();
        	String[] data = nextLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        	data[title] = data[title].replace("\"\"","\"");
        	if(data[title].charAt(0) == '"') {
        		data[title] = data[title].substring(1,data[title].length()-1);
        	}
        	if(data[title].charAt(0) == '\'') {
        		data[title] = data[title].substring(1,data[title].length()-1);
        	}
        	String providers = "";
        	if(data[Netflix].equals("1")) providers += "Netflix";
        	if(data[Hulu].equals("1")) providers += "Hulu";
        	if(data[Prime].equals("1")) providers += "Prime Video";
        	if(data[Disney].equals("1")) providers += "Disney+";
        	list.add(new Show(data[title], Integer.parseInt(data[year]), Integer.parseInt(data[rotTom].replace("/100","")), providers));
        }
        sc.close();  //closes the scanner
        return list;
    }
    
}
