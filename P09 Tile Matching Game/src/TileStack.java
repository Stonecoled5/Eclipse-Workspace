
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
 * Keeps track of the updated Tile stack
 * @author Cole Johnstone
 *
 */
public class TileStack implements Iterable<Tile>, StackADT<Tile>{
	private Node top;
	private int size;
	
	/**
	 * Constructor that creates an empty stack of Tiles
	 */
	public TileStack(){
		top = null;
		size = 0;
	}
	
	/**
	 * If the Tile stack is empty will return true, else false
	 * @return boolean
	 */
	@Override
	public boolean isEmpty(){
		if(size == 0) return true;
		return false;
	}
	
	/**
	 * Returns the size of the tile stack
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}	
	
	/**
	 * Pushes the provided tile at top of this stack
	 * and updates size
	 * @param Tile
	 */
	@Override
	public void push(Tile tile){
		Node node = new Node(tile);
		node.setNext(top);
		top = node;
		size++;
	}
	
	/**
	 * Removes and returns the tile at the top of this stack
	 * and updates size
	 * @return Tile
	 */
	@Override
	public Tile pop() {
		if(size == 0) return null;
		Tile pop = top.getTile();
		top = top.getNext();
		size--;
		return pop;
	}
	
	/**
	 * Returns the tile at the top of this stack
	 * @return Tile
	 */
	@Override
	public Tile peek() {
		if(size == 0) return null;
		return top.getTile();
	}
	/**
	 * Returns an iterator to iterate through this stack starting from its top
	 * @return java.util.Iterator<Tile>
	 */
	@Override
	public Iterator<Tile> iterator(){
		return new TileListIterator(top);
	}
}
