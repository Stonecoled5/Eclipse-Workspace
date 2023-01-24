
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P10 Binary Bookshelf
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
import java.util.ArrayList;

/**
 * A binary search tree version of the sorted Bookshelf, where all Books are
 * sorted based on (in decreasing order of importance) the Attributes contained
 * in the sortList. Books are compared first based on their authors, and then on
 * the other Attributes in the order they appear in the sortList.
 * 
 * @author Cole Johnstone
 *
 */
public class BinaryBookshelf {
	private TreeNode<Book> root; // The root node of the BST
	private int size; // The number of nodes currently contained in the BST
	private Attribute[] sortList; // The ordered array of attributes by which the BST nodes are sorted

	/**
	 * One-argument constructor, initializes an empty BinaryBookshelf
	 * 
	 * @param sortList
	 * @throws IllegalArgumentException
	 */
	public BinaryBookshelf(Attribute[] sortList) throws IllegalArgumentException {
		// size must be 4
		if (sortList.length != 4)
			throw new IllegalArgumentException("No content in sortList");

		// first item in sortList must be AUTHOR
		if (sortList[0] != Attribute.AUTHOR)
			throw new IllegalArgumentException("Author not first attribute");

		// makes sure no items in sortList match each other
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (sortList[i] == sortList[j] && i != j)
					throw new IllegalArgumentException("Duplicate Attributes detected");

		this.sortList = sortList;
		size = 0;
		root = null;
	}

	/**
	 * Resets the BST to be empty
	 */
	public void clear() {
		size = 0;
		root = null;
	}

	/**
	 * Searches for the input book in the bookshelf Complexity: O(n)
	 * 
	 * @param book
	 * @return boolean
	 */
	public boolean contains(Book book) {
		return containsHelper(book, root);
	}

	/**
	 * Recursive helper method; searches for the input book in the subtree rooted at
	 * current
	 * 
	 * @param book
	 * @param current
	 * @return boolean
	 */
	protected boolean containsHelper(Book book, TreeNode<Book> current) {
		// if start of tree or end of tree return false since no matches
		if (current == null)
			return false;

		// match
		if (book.equals(current.getData()))
			return true;

		// search farthest left first
		boolean bool1 = containsHelper(book, current.getLeft());

		if (bool1)
			return true;

		// then go through right side of each node and tree
		boolean bool2 = containsHelper(book, current.getRight());

		return bool2;
	}

	/**
	 * Provides a String-formatted list of the attributes in the order in which they
	 * are used
	 * 
	 * @return string
	 */
	public String getAttributeOrder() {
		String string = "";
		// add each item in array in incremental order to a string
		for (int i = 0; i < 4; i++) {
			string += i + 1 + ":" + sortList[i].toString() + " ";
		}
		// get rid of the space at the end
		return string.substring(0, string.length() - 1);
	}

	/**
	 * Returns a list of books in the bookshelf written by the given author
	 * 
	 * @param authorName
	 * @return ArrayList<Book>
	 */
	public ArrayList<Book> getBooksByAuthor(String authorName) {
		return getBooksByAuthorHelper(authorName, root);
	}

	/**
	 * Recursive helper method; returns a list of books in the subtree rooted at
	 * current which were written by the given author
	 * 
	 * @param authorName
	 * @param current
	 * @return ArrayList<Book>
	 */
	protected ArrayList<Book> getBooksByAuthorHelper(String authorName, TreeNode<Book> current) {
		ArrayList<Book> list = new ArrayList<Book>();

		// if empty tree or end of tree
		if (current == null)
			return list;

		// matching author add to list
		if (current.getData().getAuthor().equals(authorName)) {
			list.add(current.getData());
		}

		list.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));

		list.addAll(getBooksByAuthorHelper(authorName, current.getRight()));

		return list;
	}

	/**
	 * Returns a shallow copy of the root node so that test tree structures may be
	 * constructed manually rather than by using the insertBook() method
	 * 
	 * @return root
	 */
	protected TreeNode<Book> getRoot() {
		TreeNode<Book> newRoot = root;
		return newRoot;
	}

	/**
	 * Adds a new Book to the BST in sorted order, relative to this BST's sortList
	 * attributes
	 * 
	 * @param book
	 */
	public void insertBook(Book book) {
		/*
		 * If the tree is empty, return a new node
		 */
		if (root == null) {
			root = new TreeNode<Book>(book);
			size++;
			return;
		}

		insertBookHelper(book, root);
	}

	/**
	 * Recursive helper method; adds the given Book to the subtree rooted at
	 * current.
	 * 
	 * @param book
	 * @param current
	 * @throws IllegalArgumentException
	 */
	protected void insertBookHelper(Book book, TreeNode<Book> current) throws IllegalArgumentException {
		/* Otherwise, recur down the tree */
		for (int i = 0; i < sortList.length; i++) {
			// if duplicate book throw exception
			if (book.equals(current.getData()))
				throw new IllegalArgumentException("Duplicate Book");
			// if there is a null node in the next position on the left, place new book
			if (book.compareTo(current.getData(), sortList[i]) < 0)
				if (current.getLeft() == null) {
					size++;
					current.setLeft(new TreeNode<Book>(book));
					return;
				} else
					insertBookHelper(book, current.getLeft());
			// if there is a null node in the next position on the right, place new book
			if (book.compareTo(current.getData(), sortList[i]) > 0)
				if (current.getRight() == null) {
					size++;
					current.setRight(new TreeNode<Book>(book));
					return;
				} else
					insertBookHelper(book, current.getRight());
		}
	}

	/**
	 * Determine whether the BST is empty Complexity: O(1)
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Get the number of nodes currently in the BST Worst Case Complexity: O(1)
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * Creates and returns an in-order traversal of the BST, with each Book on a
	 * separate line Complexity: O(n)
	 */
	public String toString() {
		return toStringHelper(root);
	}

	/**
	 * Recursive helper method; creates and returns the String representation of the
	 * subtree rooted at the current node
	 * 
	 * @param current
	 * @return string
	 */
	protected String toStringHelper(TreeNode<Book> current) {
		String string = "";
		if (current != null) {
			// runs an in order traversal of the BST
			string += toStringHelper(current.getLeft());
			string += current.toString() + "\n";
			string += toStringHelper(current.getRight());
		}
		return string;
	}
}
