
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
import java.util.NoSuchElementException;

/**
 * This class iterates through the list designated by the head given in the
 * constructor class;
 * 
 * @author Cole Johnstone
 *
 */
public class TileListIterator implements Iterator<Tile> {
	private Node node;

	/**
	 * Creates a new iterator to iterate through a list of tiles starting from its
	 * head head is a reference to the head of the linked list of tiles
	 * 
	 * @param head
	 */
	public TileListIterator(Node head) {
		node = head;
	}

	/**
	 * Tests if there is a next value
	 * 
	 * @return boolean
	 */
	@Override
	public boolean hasNext() {
		if (node != null)
			return true;
		return false;
	}

	/**
	 * Returns the next Tile in the iteration
	 * 
	 * @return Tile
	 * @throws NoSuchElementException
	 */
	@Override
	public Tile next() throws NoSuchElementException {
		// throws an exception if the current node is null
		if (node == null)
			throw new NoSuchElementException("There are no more values");
		// gets current value of tile then iterates
		Tile value = node.getTile();
		node = node.getNext();
		return value;
	}
}
