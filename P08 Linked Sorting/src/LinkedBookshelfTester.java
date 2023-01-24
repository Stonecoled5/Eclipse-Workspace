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
 * Tester class for LinkedNode and LinkedBookshelf
 * 
 * @author Cole Johnstone
 *
 */
public class LinkedBookshelfTester {
	/**
	 * tests the LinkedNode class
	 * 
	 * @return boolean
	 */
	public static boolean testLinkedNode() {
		LinkedNode<Integer> head = new LinkedNode<Integer>(3);
		if (head.getData() != 3) {
			return false;
		}
		head.setNext(new LinkedNode<Integer>(5));
		if (head.getNext() == null) {
			return false;
		}
		return true;
	}

	/**
	 * Tests the clear method
	 * 
	 * @return boolean
	 */
	public static boolean testClear() {
		LinkedBookshelf shelf = new LinkedBookshelf();
		shelf.appendBook(new Book("Reading", 50, "Smith", "Bob"));
		shelf.appendBook(new Book("Writing", 69, "Johnston", "Larry"));
		shelf.clear();
		try {
			if (shelf.size() == 0) {
				shelf.getFirst();
			}
		} catch (Exception e) {
			try {
				shelf.getLast();
				return false;
			} catch (Exception e1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests the appendBook() method
	 * 
	 * @return boolean
	 */
	public static boolean testAddBooks() {
		LinkedBookshelf shelf = new LinkedBookshelf();
		shelf.appendBook(new Book("Reading", 50, "Smith", "Bob"));
		shelf.appendBook(new Book("Writing", 69, "Johnston", "Larry"));
		if (shelf.getLast().getPageCount() == 69) {
			if (shelf.size() == 2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests the sort() method
	 * 
	 * @return boolean
	 */
	public static boolean testSortBooks() {
		Book.resetGenerator();
		boolean yes = false;
		LinkedBookshelf shelf = new LinkedBookshelf();
		shelf.appendBook(new Book("Reading", 50, "Smith", "Bob"));
		shelf.appendBook(new Book("Writing", 69, "Johnston", "Larry"));
		shelf.appendBook(new Book("Ballin", 43, "Poland", "Harry"));
		shelf.appendBook(new Book("Poopin", 40, "Quam", "Randall"));
		LinkedBookshelf.sort(shelf, Attribute.PAGECOUNT);
		if (shelf.get(0).getPageCount() == 40) {
			yes = true;
		}
		System.out.println(shelf);
		if (yes)
			return true;
		return false;
	}

	/**
	 * runs all of the tests
	 * 
	 * @return boolean
	 */
	public static boolean runAllTests() {
		if (testLinkedNode() == true) {
			if (testClear() == true) {
				if (testAddBooks() == true) {
					if (testSortBooks() == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * main method for LinkedBookshelfTester
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean value = runAllTests();
		System.out.println(value);
	}
}
