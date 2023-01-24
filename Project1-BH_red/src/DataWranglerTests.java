// --== CS400 Project One File Header ==--
// Name: Cole Johnstone
// CSL Username: johnstone
// Email: cjohnstone@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Test class for both the show and showloader class
 * 
 * @author Cole Johnstone
 *
 */
public class DataWranglerTests {
	/**
	 * This test method tests the functionality of the constructor Show() and its
	 * retrieval methods
	 * 
	 * @return boolean
	 */
	public static boolean test1() {
		Show show = new Show("Breaking Bad", 2008, 100, "Netflix");
		if (!show.getTitle().equals("Breaking Bad"))
			return false;
		if (show.getYear() != 2008)
			return false;
		if (show.getRating() != 100)
			return false;

		return true;
	}

	/**
	 * This test method tests the functionality of the isAvailableOn method. It
	 * should correctly return true if the name of the service is in the providers
	 * string false if it is not It also tests if it properly deals with null values
	 * 
	 * @return boolean
	 */
	public static boolean test2() {
		Show show = new Show("Breaking Bad", 2008, 100, "Netflix");
		if (show.isAvailableOn("Netflix") != true)
			return false;
		if (show.isAvailableOn("Hulu") != false)
			return false;
		if (show.isAvailableOn(null) != false)
			return false;
		return true;
	}

	/**
	 * This test method tests the compareTo method and sees if it gives the correct
	 * comparative number in order to create a descending list
	 * 
	 * @return
	 */
	public static boolean test3() {
		Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
		Show show2 = new Show("Dark", 2017, 93, "Netflix");
		if (show1.compareTo(show2) != 1)
			return false;
		if (show1.compareTo(show1) != 0)
			return false;
		if (show2.compareTo(show1) != -1)
			return false;
		return true;
	}
	
	/**
	 * This test method tests if the loadShows method in ShowLoader:
	 * - doesn't throw any unexpected error, but throws one when the file doesn't exist
	 * - correctly creates and stores the titles, years, ratings and providers of each show
	 * @return boolean
	 */
	public static boolean test4() {
		ShowLoader loader = new ShowLoader();
		try {
			List<IShow> list = loader.loadShows(
					"C:\\Users\\Cole Johnstone\\OneDrive\\Documents\\eclipse-workspace\\Project1-BH_red\\src\\tv_shows.csv");
			if (!list.get(0).getTitle().equals("Breaking Bad")) {
				System.out.println("Basic Title failed");
				return false;
			}
			if (list.get(2).getYear() != 2013) {
				System.out.println("Year failed");
				return false;
			}
			if (list.get(2).getRating() != 95) {
				System.out.println("Rating failed");
				return false;}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		try {
			List<IShow> list1 = loader.loadShows(
					"C:\\Users\\Cole Johnstone\\OneDrive\\Documents\\eclipse-workspace\\Project1-BH_red\\src\\shows.csv");
		} catch (FileNotFoundException e1) {
			return true;
		} catch (Exception e3) {
			System.out.print(e3);
			return false;
		}
		return false;
	}
	
	/**
	 * This test method tests if the method loadShows properly changes and adapts
	 * to the titles including:
	 *  - keeping commas within quotes inside the title
	 *  - getting rid of quotes both singular and double on the outside of the title
	 *  - replacing double double quotes with one double quote
	 *  - all the while not throwing an exception 
	 * @return boolean
	 */
	public static boolean test5() {
		ShowLoader loader = new ShowLoader();
		try {
			List<IShow> list = loader.loadShows(
					"C:\\Users\\Cole Johnstone\\OneDrive\\Documents\\eclipse-workspace\\Project1-BH_red\\src\\tv_shows.csv");
		if (!list.get(44).getTitle().equals("Love, Death & Robots")) {
			System.out.println(list.get(44).getTitle());
			return false;
		}
		if (!list.get(4541).getTitle().equals("About Joey")) {
			System.out.println(list.get(4541).getTitle());
			System.out.println("Single quotes inside other quotes failed");
			return false;}
		if (!list.get(2627).getTitle().equals("Say \"I Love You.\"")) {
			System.out.println(list.get(2627).getTitle());
			System.out.println("Quotes inside other quotes failed");
			return false;}
		if (!list.get(2633).getTitle().equals("頭文字D First Stage")) {
			System.out.println(list.get(2633).getTitle());
			System.out.println("Strange characters fail");
				return false;}
		if (!list.get(5367).getTitle().equals("Fearless Adventures with Jack Randall")) {
			System.out.println(list.get(5367).getTitle());
			System.out.println("Strange characters fail");
				return false;}
		return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	/**
	 * First test method for combining classes with Backend Developer
	 * @return
	 */
	public static boolean test6() {
		Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
		Show show2 = new Show("F.R.I.E.N.D.S.", 1997, 99, "Hulu");
		Show show3 = new Show("The Office", 2006,97, "Netflix");
		ShowSearcherBackend searcher = new ShowSearcherBackend();
		searcher.addShow(show1);
		searcher.addShow(show2);
		searcher.addShow(show3);
		if(searcher.getNumberOfShows() != 5) return false;
		if(searcher.getProviderFilter("Netflix")) return false;
		searcher.setProviderFilter("Netflix", true);
		searcher.setProviderFilter("Hulu", false);
		if(!searcher.getProviderFilter("Netflix")) return false;
		return true;
	}
	
	/**
	 * Second test method for combining classes with Backend Developer
	 * @return boolean
	 */
	public static boolean test7() {
		Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
		Show show2 = new Show("F.R.I.E.N.D.S.", 1997, 99, "Hulu");
		Show show3 = new Show("The Office", 2006, 97, "Netflix");
		ShowSearcherBackend searcher = new ShowSearcherBackend();
		searcher.addShow(show1);
		searcher.addShow(show2);
		searcher.addShow(show3);
		if (searcher.searchByTitleWord("Breaking").size() != 1)
			return false;
		try {
			searcher.searchByTitleWord("Cheese");
			return false;
		} catch (NoSuchElementException e) {
			try {
				List<IShow> list = searcher.searchByYear(1997);
				if (list.size() != 1)
					return false;
				if (!list.get(0).getTitle().equals("F.R.I.E.N.D.S."))
					return false;
			} catch (Exception e2) {
				System.out.println(e2);
				return false;
			}
			try {
				searcher.searchByYear(1990);
			} catch (NoSuchElementException e3) {
				return true;
			} catch (Exception e4) {
				System.out.println(e4);
				return false;
			}
		} catch (Exception e1) {
			System.out.println(e1);
			return false;
		}
		return false;
	}
	
	/**
	 * The first testing method for my Partner the FrontendDevelopers code
	 * @return boolean
	 */
	public static boolean test8() {
		// Create test data
        String input = "test\n2008";
        String expected = "Choose a year that you would like to search for: Please enter a valid integer.\n"
        		+ "Choose a year that you would like to search for: Found 1/5 matches.\n"
        		+ "1. Breaking Bad\n"
        		+ "    100/100 (2008) on: Netflix\n";

        try {
        	// Create and add new shows to backend 
            ShowSearcherBackend backend = new ShowSearcherBackend();
            Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
    		Show show2 = new Show("F.R.I.E.N.D.S.", 1997, 99, "Hulu");
    		Show show3 = new Show("The Office", 2006, 97, "Netflix");
    		backend.addShow(show1);
    		backend.addShow(show2);
    		backend.addShow(show3);
            // Construct test frontend and text UI tester
            TextUITester tester = new TextUITester(input);
            ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);

            // Try running title search
            frontend.yearSearch();
            String string = tester.checkOutput();
            return string.equals(expected);
        } catch (Exception e) {
            // Do not allow any exceptions
            return false;
        }
        
    }
	
	/**
	 * The second testing method for my Partner the FrontendDevelopers code
	 * @return boolean
	 */
	public static boolean test9() {
		// Create test data
        String input = "breaking\n";
        String expected = "Choose a word that you would like to search for: Found 1/5 matches.\n"
        		+ "1. Breaking Bad\n"
        		+ "    100/100 (2008) on: Netflix\n";
        
        try {
            // Create and add new shows to backend 
            ShowSearcherBackend backend = new ShowSearcherBackend();
            Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
    		Show show2 = new Show("F.R.I.E.N.D.S.", 1997, 99, "Hulu");
    		Show show3 = new Show("The Office", 2006, 97, "Netflix");
    		backend.addShow(show1);
    		backend.addShow(show2);
    		backend.addShow(show3);
    		//Construct test frontend and text UI tester
    		TextUITester tester = new TextUITester(input);
            ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);

            // Try running title search
            frontend.titleSearch();
            String string = tester.checkOutput();
            return string.equals(expected);
        } catch (Exception e) {
            // Do not allow any exceptions
        	System.out.println(e);
            return false;
        }
	}
	
	/**
	 * Main method for DataWranglerTests class that runs all of the tests
	 * @param args
	 */
	public static void main(String[] args) {
		if (test1()) {
            System.out.println("Test 1 passed");
        } else {
            System.out.println("Test 1 failed");
        }
		
        if (test2()) {
            System.out.println("Test 2 passed");
        } else {
            System.out.println("Test 2 failed");
        }

       
        if (test3()) {
            System.out.println("Test 3 passed");
        } else {
            System.out.println("Test 3 failed");
        }

       
        if (test4()) {
            System.out.println("Test 4 passed");
        } else {
            System.out.println("Test 4 failed");
        }

       
        if (test5()) {
            System.out.println("Test 5 passed");
        } else {
            System.out.println("Test 5 failed");
        }
        
        
        if (test6()) {
            System.out.println("Test 6 passed");
        } else {
            System.out.println("Test 6 failed");
        }
        
        
        if (test7()) {
            System.out.println("Test 7 passed");
        } else {
            System.out.println("Test 7 failed");
        }
        
        
        if (test8()) {
            System.out.println("Test 8 passed");
        } else {
            System.out.println("Test 8 failed");
        }
        
        
        if (test9()) {
            System.out.println("Test 9 passed");
        } else {
            System.out.println("Test 9 failed");
        }
    }
}

