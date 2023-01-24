import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FRONTENDTests {
	
	/**
	 * Test whether the main command loop can be successfully entered, reject
     * invalid commands, and be exited
	 */
	@Test
	public void test1() {
		String input = "invalid\nq\n";
		String expected = "Welcome to the Team Builder App!\n"
				+ "=================================\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Please enter a valid command from the menu above.\n"
				+ "Choose a command from the menu above: ";
            // Construct test frontend and text UI tester
            TextUITester tester = new TextUITester(input);
            TeamBuilderFrontend frontend = new TeamBuilderFrontend(new TeamBuilderBackendFrontend());
            
            // Try running command loop
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            assertEquals(output, expected);
	}

	/**
	 * Tests if the age and batting average commands work and outputs a list of players
	 * testing the printPlayers method
	 */
	@Test
	public void test2() {
		String input = "a\n30\nb\n20\nq\n";
		String expected = "Welcome to the Team Builder App!\n"
				+ "=================================\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Choose a maximum age to search for: \n"
				+ "Bob Vance -> Age: 32 | BA: 300.0 | HR: 60 | SS: 25.0\n"
				+ "Jamaal Adams -> Age: 24 | BA: 230.0 | HR: 47 | SS: 33.0\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Choose a minimum batting average to search for: \n"
				+ "Bob Vance -> Age: 32 | BA: 300.0 | HR: 60 | SS: 25.0\n"
				+ "Jamaal Adams -> Age: 24 | BA: 230.0 | HR: 47 | SS: 33.0\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: ";
		try {
            // Construct test frontend and text UI tester
            TextUITester tester = new TextUITester(input);
            TeamBuilderFrontend frontend = new TeamBuilderFrontend(new TeamBuilderBackendFrontend());
            
            // Try running command loop
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            assertEquals(output, expected);
            if(output.equals(expected)) System.out.println("Test 2 passed");
            else {
            	System.out.println(expected);
            	System.out.println(output);
            	System.out.println("Test 2 failed since input and output weren't equal");
            }
        } catch (Exception e) {
            // Do not allow any exceptions
            System.out.println("Test 2 failed by throwing an exception");
        }
	}
	
	/**
	 * Tests if the commands for sprint speed and home runs work correctly and output a list
	 * of players provided by the backend
	 */
	@Test
	public void test3() {
		String input = "s\n20\nh\n40\nq\n";
		String expected = "Welcome to the Team Builder App!\n"
				+ "=================================\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Choose a minimum sprint speed to search for: \n"
				+ "Jamaal Adams -> Age: 24 | BA: 230.0 | HR: 47 | SS: 33.0\n"
				+ "Bob Vance -> Age: 32 | BA: 300.0 | HR: 60 | SS: 25.0\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Choose a minimum number of home runs to search for: \n"
				+ "30\n"
				+ "Bob Vance -> Age: 32 | BA: 300.0 | HR: 60 | SS: 25.0\n"
				+ "Jamaal Adams -> Age: 24 | BA: 230.0 | HR: 47 | SS: 33.0\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: ";
		
		try {
            // Construct test frontend and text UI tester
            TextUITester tester = new TextUITester(input);
            TeamBuilderFrontend frontend = new TeamBuilderFrontend(new TeamBuilderBackendFrontend());
            
            // Try running command loop
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            if(output.equals(expected)) System.out.println("Test 3 passed");
            else {
            	System.out.println(expected);
            	System.out.println(output);
            	System.out.println("Test 3 failed since input and output weren't equal");
            }
        } catch (Exception e) {
            // Do not allow any exceptions
            System.out.println("Test 3 failed by throwing an exception");
        }
	}
	
	/**
	 * Tests the add command and display team to make sure the player is properly added to 
	 * the users team
	 */
	@Test
	public void test4() {
		String input = "a\n40\n+\nBob Vance\n+\nJamaal Adams\nd\nq\n";
		String expected = "Welcome to the Team Builder App!\n"
				+ "=================================\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: \n"
				+ "Choose a maximum age to search for: \n"
				+ "Bob Vance -> Age: 32 | BA: 300.0 | HR: 60 | SS: 25.0\n"
				+ "Jamaal Adams -> Age: 24 | BA: 230.0 | HR: 47 | SS: 33.0\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: \n"
				+ "Name a player to add: \n"
				+ "Player was successfully added to your team.\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: \n"
				+ "Name a player to add: \n"
				+ "Player was successfully added to your team.\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: \n"
				+ "Bob Vance, Jamaal Adams\n"
				+ "Team Average Stats:\n"
				+ "BA: 265.0 | HR: 53.0 | SS: 29.0\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: ";
		try {
            // Construct test frontend and text UI tester
            TextUITester tester = new TextUITester(input);
            TeamBuilderFrontend frontend = new TeamBuilderFrontend(new TeamBuilderBackendFrontend());
            
            // Try running command loop
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            if(output.equals(expected)) System.out.println("Test 4 passed");
            else {
            	System.out.println(expected);
            	System.out.println(output);
            	System.out.println("Test 4 failed since input and output weren't equal");
            }
        } catch (Exception e) {
            // Do not allow any exceptions
            System.out.println("Test 4 failed by throwing an exception");
        }
	}
	
	/**
	 * Tests if the remove function works after adding players to a team
	 * the statistics aren't accurate as they were intended for the two players 
	 * I created
	 */
	@Test
	public void test5() {
		String input = "a\n40\n+\nBob Vance\n+\nJamaal Adams\nr\nBob Vance\nd\nq\n";
		String expected = "Welcome to the Team Builder App!\n"
				+ "=================================\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Choose a maximum age to search for: \n"
				+ "Bob Vance -> Age: 32 | BA: 300.0 | HR: 60 | SS: 25.0\n"
				+ "Jamaal Adams -> Age: 24 | BA: 230.0 | HR: 47 | SS: 33.0\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Name a player to add: \n"
				+ "Player was successfully added to your team.\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Name a player to add: \n"
				+ "Player was successfully added to your team.\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: "
				+ "Name a player to remove: \n"
				+ "Player was successfully removed from your team.\n"
				+ "Choose a command from the menu above: \n"
				+ "Jamaal Adams\n"
				+ "Team Average Stats:\n"
				+ "BA: 265.0 | HR: 53.0 | SS: 29.0\n"
				+ "Command Menu:\n"
				+ "    1) Search by [A]ge of player\n"
				+ "    2) Search by [B]atting average\n"
				+ "    3) Search by [S]print speed\n"
				+ "    4) Search by [H]ome runs\n"
				+ "    5) [+]Add player to your team\n"
				+ "    6) [R]emove player from your team\n"
				+ "    7) [D]isplay team\n"
				+ "    8) [Q]uit\n"
				+ "Choose a command from the menu above: ";
		try {
            // Construct test frontend and text UI tester
            TextUITester tester = new TextUITester(input);
            TeamBuilderFrontend frontend = new TeamBuilderFrontend(new TeamBuilderBackendFrontend());
            
            // Try running command loop
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            if(output.equals(expected)) System.out.println("Test 5 passed");
            else {
            	System.out.println(expected);
            	System.out.println(output);
            	System.out.println("Test 5 failed since input and output weren't equal");
            }
        } catch (Exception e) {
            // Do not allow any exceptions
            System.out.println("Test 5 failed by throwing an exception");
        }
	}
	
	/*public static void main(String[] args) {
		TeamBuilderFrontend frontend = new TeamBuilderFrontend(new TeamBuilderBackendFrontend());
		frontend.runCommandLoop();
	}*/
}
