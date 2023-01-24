import java.util.List;
import java.util.Scanner;

public class TeamBuilderFrontend implements ITeamBuilderFrontend{
	
	private List<IPlayer> list;
	
	/**
	 * The team builder backend to perform many operations with
	 */
	private final ITeamBuilderBackend backend;

	/**
	 * The source for command input
	 */
	private final Scanner inputReader;

	/**
	 * Construct a new team builder frontend
	 *
	 * @param backend the search backend to use
	 */
	public TeamBuilderFrontend(ITeamBuilderBackend backend) {
		this.backend = backend;
		this.inputReader = new Scanner(System.in);
	}

	/**
	 * Construct a new team builder frontend
	 *
	 * @param input a string to use as input commands
	 * @param backend the search backend to use
	 */
	public TeamBuilderFrontend(String input, ITeamBuilderBackend backend) {
		this.backend = backend;
		this.inputReader = new Scanner(input);
	}
	
	/**
     * Run the main application command loop
     */
	@Override
    public void runCommandLoop() {
		// Print welcome message and display command menu
				System.out.println("Welcome to the Team Builder App!");
				System.out.println("=================================");
				this.displayCommandMenu();

				// Main loop
				boolean running = true;
				while (running) {
					// Continuously read commands
					System.out.print("Choose a command from the menu above: ");
					String command = this.inputReader.nextLine();

					switch (command) {
					// Age search
					case "1":
					case "a":
					case "A":
						System.out.println("Choose a maximum age to search for: ");
						String age = this.inputReader.nextLine();
						try {
							list = backend.searchByAge(Integer.parseInt(age));}
						catch(NumberFormatException e) {
							System.out.println("Please enter an integer value.");
							this.displayCommandMenu();
							break;
						}
						this.printPlayers(list);
						this.displayCommandMenu();
						break;

					// batting average search
					case "2":
					case "b":
					case "B":
						System.out.println("Choose a minimum batting average to search for: ");
						String BA = this.inputReader.nextLine();
						try {
						list = backend.searchByBattingAverage(Double.parseDouble(BA));}
						catch(NumberFormatException e) {
							System.out.println("Please enter a double value.");
							this.displayCommandMenu();
							break;
						}
						this.printPlayers(list);
						this.displayCommandMenu();
						break;

					// sprint speed search
					case "3":
					case "s":
					case "S":
						System.out.println("Choose a minimum sprint speed to search for: ");
						String speed = this.inputReader.nextLine();
						try {
							list = backend.searchBySprintSpeed(Double.parseDouble(speed));}
						catch(NumberFormatException e) {
							System.out.println("Please enter a double value.");
							this.displayCommandMenu();
							break;
						}
						this.printPlayers(list);
						this.displayCommandMenu();
						break;
					
					// home run search
					case "4":
					case "h":
					case "H":
						System.out.println("Choose a minimum number of home runs to search for: ");
						String HR = this.inputReader.nextLine();
						try {
							list = backend.searchByHomeRuns(Integer.parseInt(HR));}
						catch(NumberFormatException e) {
							System.out.println("Please enter an integer value.");
							this.displayCommandMenu();
							break;
						}
						this.printPlayers(list);
						this.displayCommandMenu();
						break;
						
					// add player
					case "5":
					case "+":
						System.out.println("Name a player to add: ");
						String player = this.inputReader.nextLine();
						boolean found = nameSearcherAdd(player);
						if(found) System.out.println("Player was successfully added to your team.");
						else System.out.println("There were no found players with that name.");
						this.displayCommandMenu();
						break;
						
					// remove player from team
					case "6":
					case "r":
					case "R":
						System.out.println("Name a player to remove: ");
						String remove = this.inputReader.nextLine();
						boolean removed = nameSearcherRemove(remove);
						if(removed) System.out.println("Player was successfully removed from your team.");
						else System.out.println("There were no found players on your team with that name.");
						break;	
						
					// display team
					case "7":
					case "d":
					case "D":
						this.printTeam(backend.getTeam());
						this.displayCommandMenu();
						break;	
						
					// Quit
					case "8":
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
			public void displayCommandMenu() {
				System.out.println("Command Menu:");
				System.out.println("    1) Search by [A]ge of player");
				System.out.println("    2) Search by [B]atting average");
				System.out.println("    3) Search by [S]print speed");
				System.out.println("    4) Search by [H]ome runs");
				System.out.println("    5) [+]Add player to your team");
				System.out.println("    6) [R]emove player from your team");
				System.out.println("    7) [D]isplay team");
				System.out.println("    8) [Q]uit");
			}
    	

    /**
     * Print out a list of players
     *
     * @param players the players to print
     */
	@Override
    public void printPlayers(List<IPlayer> players) {
    	for(IPlayer player : players) {
    		System.out.println(player.getFirstName() + " " + player.getLastName() + " -> Age: "
    				+ player.getAge() + " | BA: " + player.getBattingAverage() + " | HR: "
    				+ player.getHomeRuns() + " | SS: " + player.getSprintSpeed());
    	}
    }

    /**
     * Print out a team with some average statistics
     *
     * @param team the team to print out
     */
	@Override
    public void printTeam(ITeam team) {
    	for(int i = 0; i < team.size(); i++) {
    		System.out.print(team.get(i).getFirstName() + " " + team.get(i).getLastName());
    		if(i != team.size() -1) {
    			System.out.print(", ");
    		}
    	}
    	System.out.println();
    	System.out.println("Team Average Stats:\nBA: " + team.averageStat("batting average") 
    		+ " | HR: " + team.averageStat("home runs") + " | SS: " + team.averageStat("sprint speed"));
    	
    }
	
	/**
	 * Searches for a player based on name
	 * 
	 * @param player
	 * @return boolean based on if the player was found
	 */
	public boolean nameSearcherAdd(String player) {
		String[] name = player.split(" ");
		boolean found = false;
		if(list == null) {
			System.out.println("Search for players by a statistic first!");
			return false;
		}
		for(int i = 0; i < list.size(); i++) {
			if (name[0].equals(list.get(i).getFirstName()) && 
					name[1].equals(list.get(i).getLastName())) {
				backend.addPlayerToTeam(list.get(i));
				found = true;
				break;
			}
		}
		return found;
	}
	/**
	 * Searches for a player based on name
	 * 
	 * @param player
	 * @return boolean based on if the player was found
	 */
	public boolean nameSearcherRemove(String player) {
		String[] name = player.split(" ");
		boolean found = false;
		if(list == null) {
			System.out.println("Search for players by a statistic first!");
			return false;
		}
		for(int i = 0; i < list.size(); i++) {
			if (name[0].equals(list.get(i).getFirstName()) && 
					name[1].equals(list.get(i).getLastName())) {
				backend.removePlayerFromTeam(list.get(i));
				found = true;
				break;
			}
		}
		return found;
	}
}
