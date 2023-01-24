
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Tile Matching Game
// Course:   CS 300 Fall 2021
//
// Author:   Cole Johnstone
// Email:    cjohnstone@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    -
// Partner Email:   -
// Partner Lecturer's Name: -
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         -
// Online Sources:  zybooks
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
/**
 * Tester Class for all the tile matching game classes
 * @author Cole Johnstone
 *
 */
public class TileMatchingTester {
	/**
	 * tests the Tile.equals method
	 * @return boolean
	 */
	public static boolean tileEqualsTester() {
		Tile tile = new Tile(Color.BLACK);
		String string = "test";
		try{
			if(tile.equals((string)))//tests if wrong object goes through
				return false;
		
			Tile tile2 = new Tile(Color.BLACK);
			if(!tile.equals(tile2))//tests if they do equal each other when
				return false;	//they should
		
			tile2 = new Tile(Color.BLUE);
			if(tile.equals(tile2))//tests if wrong colors are false
				return false;
		
			tile2 = null;
			if(tile.equals(tile2))//tests a null object thrown in
				return false;
		
			if(!tile.equals(tile))//tests if a tile is equal to itself
				return false;}
		catch(Exception e){return false;}
		return true;
	}
	/**
	 * Tests the TileListIterator class
	 * @return boolean
	 */
	public static boolean tileListIteratorTester() {
		TileListIterator iterator = new TileListIterator(new Node(
				new Tile(Color.BLACK),new Node(new Tile(Color.BLUE))));
		try {
			if(!iterator.hasNext())//tests if the hasNext() method works
				return false;
			if(!iterator.next().equals(new Tile(Color.BLACK)))//tests if next()
				return false;//method works properly
			if(!iterator.next().equals(new Tile(Color.BLUE)))//tests if iteration
				return false;// works properly
		}
		catch(Exception e) {
			return false;
		}
		try {//tests if exception is thrown when there is no next value
			iterator.next();
		}
		catch(Exception e1) {
			return true;
		}
		return false;
	}
	/**
	 * Tests the TileStack class
	 * @return boolean
	 */
	public static boolean tileStackTester() {
		try {	
			TileStack stack = new TileStack();
			if(!stack.isEmpty())//checks if isEmpty() method works
				return false;
			
			stack.push(new Tile(Color.BLACK));
			if(stack.size() != 1 || !stack.peek().equals(new Tile(Color.BLACK)))
				return false;//tests if size, push and peek methods work properly
			
			stack.pop();
			if(stack.size() != 0 || stack.peek() != null)
				return false;//tests if size and peek methods work properly after pop
							//is used
			stack.push(new Tile(Color.BLACK));
			stack.push(new Tile(Color.BLUE));
			stack.push(new Tile(Color.ORANGE));//tests if iterator method works properly
			if(stack.iterator() instanceof Iterator<?>)
				return true;
			return false;}
		catch(Exception e) {return false;}
	}
	/**
	 * Tests the TileMatchingGame class
	 * @return boolean
	 */
	public static boolean tileMatchingGameTester() {
		try {	
			TileMatchingGame game = new TileMatchingGame(4);
			if(game.getColumnsNumber() != 4)//tests if getColumnsNumber works properly
				return false;
			//tests dropTile and column methods
			game.dropTile(new Tile(Color.BLACK),1);
			game.dropTile(new Tile(Color.BLUE),1);
			if(!game.column(0).equals(""))
				return false;
			if(!game.column(1).equals("BLUE BLACK "))
				return false;
			
			game.dropTile(new Tile(Color.BLACK),0);
			game.clearColumn(0);
			if(!game.column(0).equals(""))//tests clearColumn method
				return false;
			//tests toString method
			if(!game.toString().equals("GAME COLUMNS:\n0: \n1: "
					+ "BLUE BLACK \n2: \n3: \n"))
				return false;
			//tests restartGame method
			game.restartGame();
			for(int i = 0; i<game.getColumnsNumber();i++) {
				if(!game.column(i).equals(""))
					return false;}
			return true;
				
		}
		catch(Exception e) {
			return false;
		}
	}
	/**
	 * runs all of the testing methods in this class
	 * @return boolean
	 */
	public static boolean runAllTests(){
		if(tileEqualsTester())
			if(tileListIteratorTester())
				if(tileStackTester())
					if(tileMatchingGameTester())
						return true;
		return false;
	}
	
	/**
	 * Main method for this class
	 * @param args
	 */
	public static void main(String[] args) {
		boolean value = runAllTests();
		System.out.println(value);

	}

}
