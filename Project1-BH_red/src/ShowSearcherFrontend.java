import java.util.List;
import java.util.Scanner;

/**
 * The frontend for a show searching application
 */
public class ShowSearcherFrontend implements IShowSearcherFrontend {
	/**
	 * An array of streaming providers
	 */
	private static final String[] PROVIDERS = { "Netflix", "Hulu", "Prime Video", "Disney+" };

	/**
	 * The show searcher backend to perform search operations with
	 */
	private final IShowSearcherBackend backend;

	/**
	 * The source for command input
	 */
	private final Scanner inputReader;

	/**
	 * Construct a new show searcher frontend
	 *
	 * @param backend the search backend to use
	 */
	public ShowSearcherFrontend(IShowSearcherBackend backend) {
		this.backend = backend;
		this.inputReader = new Scanner(System.in);
	}

	/**
	 * Construct a new show searcher frontend
	 *
	 * @param input   a string to use as input commands
	 * @param backend the search backend to use
	 */
	public ShowSearcherFrontend(String input, IShowSearcherBackend backend) {
		this.backend = backend;
		this.inputReader = new Scanner(input);
	}

	/**
	 * Run the main interaction loop until the user quits
	 */
	@Override
	public void runCommandLoop() {
		// Print welcome message and display command menu
		System.out.println("Welcome to the Show Searcher App!");
		System.out.println("=================================");
		this.displayCommandMenu();

		// Main loop
		boolean running = true;
		while (running) {
			// Continuously read commands
			System.out.print("Choose a command from the menu above: ");
			String command = this.inputReader.nextLine();

			switch (command) {
			// Title search
			case "1":
			case "t":
			case "T":
				this.titleSearch();
				this.displayCommandMenu();
				break;

			// Year search
			case "2":
			case "y":
			case "Y":
				this.yearSearch();
				this.displayCommandMenu();
				break;

			// Change provider filter
			case "3":
			case "f":
			case "F":
				this.runFilterToggleLoop();
				this.displayCommandMenu();
				break;

			// Quit
			case "4":
			case "q":
			case "Q":
				running = false;
				break;

			// Invalid command
			default:
				System.out.println("Please enter a valid command from the menu above.");
				break;
			}
		}
	}

	/**
	 * Show the menu of available commands
	 */
	@Override
	public void displayCommandMenu() {
		System.out.println("Command Menu:");
		System.out.println("    1) Search by [T]itle Word");
		System.out.println("    2) Search by [Y]ear First Produced");
		System.out.println("    3) [F]ilter by Streaming Provider");
		System.out.println("    4) [Q]uit");
	}

	/**
	 * Run the filter toggle command's interaction loop
	 */
	public void runFilterToggleLoop() {
		// Show filter status
		this.displayFilterStatus();

		// Main loop
		boolean running = true;
		while (running) {
			// Continuously read commands
			System.out.print("Choose the provider that you'd like to toggle the filter for: ");
			String provider = this.inputReader.nextLine();

			switch (provider) {
			// Netflix
			case "1":
			case "n":
			case "N":
				this.backend.toggleProviderFilter("Netflix");
				this.displayFilterStatus();
				break;

			// Hulu
			case "2":
			case "h":
			case "H":
				this.backend.toggleProviderFilter("Hulu");
				this.displayFilterStatus();
				break;

			// Prime Video
			case "3":
			case "p":
			case "P":
				this.backend.toggleProviderFilter("Prime Video");
				this.displayFilterStatus();
				break;

			// Disney+
			case "4":
			case "d":
			case "D":
				this.backend.toggleProviderFilter("Disney+");
				this.displayFilterStatus();
				break;

			// Quit
			case "5":
			case "q":
			case "Q":
				running = false;
				break;

			// Invalid provider
			default:
				System.out.println("Please enter a valid provider.");
				break;
			}
		}
	}

	/**
	 * Print out the status of the provider filter
	 */
	public void displayFilterStatus() {
		// Print header
		System.out.println("Providers that shows are being searched for:");

		// Iterate over providers
		for (int i = 0; i < ShowSearcherFrontend.PROVIDERS.length; i++) {
			// Get this provider
			String provider = ShowSearcherFrontend.PROVIDERS[i];
			StringBuilder sb = new StringBuilder("    ");

			// Construct info string
			sb.append(i + 1);
			sb.append(") _");
			if (backend.getProviderFilter(provider)) {
				sb.append("x");
			} else {
				sb.append("_");
			}
			sb.append("_ [");
			sb.append(provider.charAt(0));
			sb.append("]");
			sb.append(provider.substring(1));

			// Print info string
			System.out.println(sb);
		}

		// Print quit option
		System.out.println("    5) [Q]uit toggling provider filters");
	}

	/**
	 * Display a list of shows
	 *
	 * @param shows the shows to display
	 */
	@Override
	public void displayShows(List<IShow> shows) {
		for (int i = 0; i < shows.size(); i++) {
			// Get show
			IShow show = shows.get(i);

			// Construct basic info
			StringBuilder sb = new StringBuilder();
			sb.append(i + 1);
			sb.append(". ");
			sb.append(show.getTitle());
			sb.append("\n    ");
			sb.append(show.getRating());
			sb.append("/100 (");
			sb.append(show.getYear());
			sb.append(") on: ");

			// Figure out which providers the show is available on, including
			// commas if necessary
			int providerN = 0;
			for (String provider : ShowSearcherFrontend.PROVIDERS) {
				if (show.isAvailableOn(provider)) {
					if (providerN > 0) {
						sb.append(", ");
					}
					sb.append(provider);
					providerN++;
				}
			}

			// Print out final info
			System.out.println(sb);
		}
	}

	/**
	 * Search for shows by title
	 */
	@Override
	public void titleSearch() {
		// Prompt user for search
		System.out.print("Choose a word that you would like to search for: ");
		String search = this.inputReader.nextLine();

		// Perform search
		List<IShow> results = backend.searchByTitleWord(search);

		// Display results
		System.out.println("Found " + results.size() + "/" + backend.getNumberOfShows() + " matches.");
		this.displayShows(results);
	}

	/**
	 * Search for shows by year
	 */
	@Override
	public void yearSearch() {
		// Prompt user for search, continually reading until a valid year is entered
		int year = -1;

		while (year < 0) {
			System.out.print("Choose a year that you would like to search for: ");
			String in = this.inputReader.nextLine();

			// Try parsing
			try {
				year = Integer.parseInt(in);

				// Make sure the parsed year is positive
				if (year < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid integer.");
			}
		}

		// Perform search
		List<IShow> results = backend.searchByYear(year);

		// Display results
		System.out.println("Found " + results.size() + "/" + backend.getNumberOfShows() + " matches.");
		this.displayShows(results);
	}
}
