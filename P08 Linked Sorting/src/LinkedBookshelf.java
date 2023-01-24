
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 Linked Sorting
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

/**
 * Class for a creation of a linked bookshelf
 * 
 * @author Cole Johnstone
 *
 */
public class LinkedBookshelf {
	private LinkedNode<Book> front; // book currently at the front of the list
	private LinkedNode<Book> back; // book currently at the back of the list
	private int size; // number of books on the bookshelf
	private Attribute sortedBy; // how the list is currently sorted

	/**
	 * Creates a new bookshelf based off of ID
	 */
	public LinkedBookshelf() {
		size = 0;
		front = null;
		back = null;
		sortedBy = Attribute.ID;
	}

	/**
	 * returns the size of the bookshelf
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * checks if the bookshelf is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (front == null && front.getNext() == null) {
			return true;
		}
		return false;
	}

	/**
	 * Prints the entire bookshelf sorted by every category
	 * 
	 * @return String of books in the bookshelf
	 */
	@Override
	public String toString() {
		String string = null;
		sort(this, sortedBy);// first sorts the shelf by attribute
		string = sortedBy.toString() + "\n";// sets the title for the method of sorting
		for (int i = 0; i < size; i++) {// adds the books in order to a string
			string += this.get(i).toString() + "\n";
		}
		return string;
	}

	/**
	 * This method returns the node at the given index if possible
	 * 
	 * @param index
	 * @return Node at given index
	 */
	public LinkedNode<Book> getNode(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index is not in range 0-" + (size - 1));
		}
		LinkedNode<Book> node = front;// assigns a key node as
										// to not disturb the front variable
		for (int i = 0; i < size; i++) {
			if (i == index) {
				break;
			}
			node = node.getNext();// looks for given index and returns correct node
		}
		return node;
	}

	/**
	 * Returns the book at the given index if possible
	 * 
	 * @param index
	 * @return Book at index
	 */
	public Book get(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index is not in range 0-" + (size - 1));
		}
		Book book = null;
		LinkedNode<Book> node = front;// assigns a key node and book as
										// to not disturb the front variable
		for (int i = 0; i < size; i++) {// runs through the shelf and returns
			if (i == index) {// the book with the correct index
				book = node.getData();
				break;
			}
			node = node.getNext();
		}
		return book;
	}

	/**
	 * returns the first book in the bookshelf
	 * 
	 * @return front
	 */
	public Book getFirst() throws NullPointerException {
		if (front == null)
			throw new NullPointerException("null value");
		return front.getData();
	}

	/**
	 * returns the last book in the bookshelf
	 * 
	 * @return back
	 */
	public Book getLast() throws NullPointerException {
		if (back == null)
			throw new NullPointerException("null value");
		return back.getData();
	}

	/**
	 * clears the bookshelf
	 */
	public void clear() {
		front.setNext(null);
		front = null;
		back = null;
		size = 0;
	}

	/**
	 * adds a new book to the bookshelf
	 * 
	 * @param toAdd
	 */
	public void appendBook(Book toAdd) {
		if (size == 0) {// if this is the first book added front and back are the same
			front = new LinkedNode<Book>(toAdd);
			back = front;
			size++;
			return;
		}
		if (size == 1) {// if this is the second book added the back is the toAdd book
			back = new LinkedNode<Book>(toAdd);
			front.setNext(back);
			size++;
			return;
		} // otherwise add the book to the back of the list and assign the new back
		back.setNext(new LinkedNode<Book>(toAdd));
		back = back.getNext();
		size++;
	}

	/**
	 * inserts a book in the correct location on the bookshelf
	 * 
	 * @param toAdd
	 */
	public void insertBook(Book toAdd) {
		LinkedNode<Book> node = new LinkedNode<Book>(toAdd);
		//at start of sorting or comparing the sorted side to the book we want to add
		//back acts as the sorted side of the linked list
		if (front == null || front.getData().compareTo(toAdd, sortedBy) >= 0) {
			node.setNext(front);
			front = node;
			size++;
			return;
		}
		else {//if in the middle of the linked list go through and place the book in the 
			//correct position
			LinkedNode<Book> current = front;
			while(current.getNext() != null && current.getNext().getData().compareTo(toAdd, sortedBy) < 0) {
				current = current.getNext();
			}
			node.setNext(current.getNext());
			current.setNext(node);
			size++;
		}
		back = getNode(size-1);
	}

	/**
	 * runs insertion sort on the provided shelf, using the given Attribute for
	 * comparing Book objects
	 * 
	 * @param b
	 * @param sortedBy
	 */
	public static void sort(LinkedBookshelf b, Attribute sortedBy) {
		b.sortedBy = sortedBy;
		if (b.size <= 1)
			return;
		LinkedNode<Book> current = b.front;
		b.front = null;
		b.size = 0;
		while (current != null) {// runs through the linked list
			LinkedNode<Book> next = current.getNext();
			b.insertBook(current.getData());
			current = next;}
		b.back = b.getNode(b.size-1);
	}

}
