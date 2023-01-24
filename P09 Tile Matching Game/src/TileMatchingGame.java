
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
 * This class models a tile matching game which consists of a certain number
 * of columns of stacks of tiles
 * @author Cole Johnstone
 *
 */
public class TileMatchingGame {
	private TileStack[] columns;
	
	/**
	 * Initializes the columns array to an array which contains an
	 * empty tile stack at each of its index positions
	 * @param columnCount
	 */
	public TileMatchingGame(int columnCount) {
		columns = new TileStack[columnCount];
		for(int i = 0; i < columnCount; i++)
			columns[i] = new TileStack();
	}
	
	/**
	 * Removes all the tiles from a column with a given index
	 * @param index
	 */
	public void clearColumn(int index) {
		columns[index] = new TileStack();
	}
	
	/**
	 * Returns a string representation of the stack of tiles at a given
	 * column index and an empty string if the stack is empty
	 * @param index
	 * @return String
	 */
	public String column(int index) {
		if(columns[index].isEmpty()) return "";
		String string ="";
		Iterator<Tile> iterator = columns[index].iterator();
		//goes through the stack at the given index and adds the
		//elements in order to a string
		while(iterator.hasNext()) {
			string += iterator.next().toString() + " ";
		}
		return string;
	}
	
	/**
	 * Drops a tile at the top of the stack located at the given column index
	 * @param tile
	 * @param index
	 */
	public void dropTile(Tile tile, int index) {
		//removes both tiles if the tile below it has the same color
		if(tile.equals(columns[index].peek())) {
				columns[index].pop();
			}
		//adds new tile on top of stack
		else {		
			columns[index].push(tile);}
	}
	
	/**
	 * Gets the number of columns in this tile matching game
	 * @return length
	 */
	public int getColumnsNumber() {
		return columns.length;
	}
	
	/**
	 * Restarts the game
	 */
	public void restartGame() {
		//clears all columns to restart game
		for(int i = 0; i < columns.length; i++)
			clearColumn(i);
	}
	
	/**
	 * Returns a string representation of this tile matching game
	 * @return String
	 */
	@Override
	public String toString() {
		String string = "GAME COLUMNS:\n";
		//creates a list of the colors in each stack in each column
		for(int i = 0; i < columns.length; i++) {
			string += (i + ": " + column(i) + "\n");
		}
		return string;
	}
}
