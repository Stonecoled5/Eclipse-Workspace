
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
 * Tester class for BinaryBookshelf and TreeNode classes
 * 
 * @author Cole Johnstone
 *
 */
public class BinaryBookshelfTester {

	/**
	 * tests the TreeNode class
	 * 
	 * @return boolean
	 */
	public static boolean testTreeNode() {
		TreeNode<Book> node = new TreeNode<Book>(new Book("Title", 62));
		Book.resetGenerator();

		// tests if TreeNode was created correctly
		if (node.getRight() != null)
			return false;
		if (node.getLeft() != null)
			return false;
		if (!node.getData().equals(new Book("Title", 62)))
			return false;
		if (!node.toString().equals("0: \"" + "Title" + "\", " + ", " + " (" + 62 + ")"))
			return false;

		Book.resetGenerator();
		TreeNode<Book> node1 = new TreeNode<Book>(new Book("Superman", 54));
		Book.resetGenerator();
		TreeNode<Book> node2 = new TreeNode<Book>(new Book("Batman", 102));
		Book.resetGenerator();

		// tests correct TreeNode getLeft, setLeft, getRight, and setRight methods
		// with a simple collection of TreeNodes
		node1.setLeft(node2);
		if (node1.getRight() != null)
			return false;
		if (!node1.getLeft().getData().equals(new Book("Batman", 102)))
			return false;
		node1.setLeft(null);
		if (node1.getLeft() != null)
			return false;
		Book.resetGenerator();

		// tests multiple-arg constructor with the getLeft, setLeft, getRight, and
		// setRight methods
		TreeNode<Book> node3 = new TreeNode<Book>(new Book("Cow", 160));
		TreeNode<Book> node4 = new TreeNode<Book>(new Book("Pig", 179));
		TreeNode<Book> node5 = new TreeNode<Book>(new Book("Chicken", 58, "Smith", "John"));
		Book.resetGenerator();
		node5.setLeft(node3);
		node5.setRight(node4);
		if (!node5.getLeft().getData().equals(new Book("Cow", 160)))
			return false;
		if (!node5.getRight().getData().equals(new Book("Pig", 179)))
			return false;
		return true;
	}

	/**
	 * Ensures that the basic methods of a BinaryBookshelf are valid, before any
	 * Books have been added to the shelf
	 * 
	 * @return boolean
	 */
	public static boolean testEmptyTree() {
		try {
			// empty attribute array test
			BinaryBookshelf bookShelf = new BinaryBookshelf(new Attribute[0]);
			return false;
		} catch (IllegalArgumentException e) {
			try {
				// attribute array with not size 4
				Attribute[] array = { Attribute.ID, Attribute.TITLE, Attribute.AUTHOR };
				BinaryBookshelf bookShelf = new BinaryBookshelf(array);
				return false;
			} catch (IllegalArgumentException e1) {
				try {
					// attribute array with multiple same items
					Attribute[] array = { Attribute.AUTHOR, Attribute.ID, Attribute.TITLE, Attribute.AUTHOR };
					BinaryBookshelf shelf = new BinaryBookshelf(array);
					return false;
				} catch (IllegalArgumentException e2) {
					try {
						// attribute array with author not in index 0
						Attribute[] array = { Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT, Attribute.AUTHOR };
						BinaryBookshelf shelf = new BinaryBookshelf(array);
						return false;
					} catch (IllegalArgumentException e3) {
						try {
							// tests valid input
							Attribute[] array = { Attribute.AUTHOR, Attribute.ID, Attribute.TITLE,
									Attribute.PAGECOUNT };
							BinaryBookshelf shelf = new BinaryBookshelf(array);
							// tests if size() works correctly
							if (shelf.size() != 0)
								return false;
							// tests if isEmpty() works correctly
							if (!shelf.isEmpty())
								return false;
							// tests if toString() works correctly
							if (!shelf.toString().equals(""))
								return false;
							// tests if getRoot() works correctly with no books
							if (shelf.getRoot() != null)
								return false;
							// tests if getAttributeOrder() works correctly
							if (!shelf.getAttributeOrder().equals("1:AUTHOR 2:ID 3:TITLE 4:PAGECOUNT"))
								return false;
							// tests if contains() works correctly with no books
							if (shelf.contains(new Book("Title", 809)))
								return false;
							// tests if getBooksByAuthor() works correctly with no books
							if (shelf.getBooksByAuthor("Smith, John").size() != 0)
								return false;
							return true;
						} catch (Exception e4) {
							// shouldn't throw exception in this try block
							return false;
						}
					}
				}
			}

		}
	}

	/**
	 * tests insertBook method
	 * 
	 * @return boolean
	 */
	public static boolean testInsertBook() {
		Attribute[] array = { Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT };
		BinaryBookshelf shelf;
		try {
			// tests correct empty shelf
			shelf = new BinaryBookshelf(array);
			if (shelf.size() != 0)
				return false;
			if (!shelf.isEmpty())
				return false;
			if (!shelf.toString().equals(""))
				return false;
			if (shelf.getRoot() != null)
				return false;

			Book.resetGenerator();

			Book book1 = new Book("Title", 506, "Smith", "John");

			// tests if insertBook works correctly with 1 book
			shelf.insertBook(book1);
			if (shelf.size() != 1)
				return false;
			if (!shelf.getRoot().getData().getAuthor().equals("Smith, John"))
				return false;
			if (!shelf.getRoot().getData().getTitle().equals("Title"))
				return false;
			if (shelf.getRoot().getData().getPageCount() != 506)
				return false;

			Book book2 = new Book("Chapter", 40, "Johnson", "Matt");
			shelf.insertBook(book2);

			// tests if insertBook works correctly with 2 different books
			if (shelf.size() != 2)
				return false;
			if (!shelf.getRoot().getLeft().getData().equals(book2))
				return false;

			Book book3 = new Book("Yesterday", 67, "Smith", "John");
			shelf.insertBook(book3);

			// tests if insertBook works correctly with 3 different books
			if (shelf.size() != 3)
				return false;
			if (!shelf.getRoot().getRight().getData().getAuthor().equals("Smith, John"))
				return false;
			if (!shelf.getRoot().getRight().getData().getTitle().equals("Yesterday"))
				return false;
			if (shelf.getRoot().getRight().getData().getPageCount() != 67)
				return false;

		}

		catch (Exception e) {
			return false;
		}
		try {
			// tests if insertBook correctly throws an exception when necessary
			Book.resetGenerator();
			shelf.insertBook(new Book("Title", 506, "Smith", "John"));
			return false;
		} catch (IllegalArgumentException e) {
			return true;
		}
	}

	/**
	 * Tests contains method
	 * 
	 * @return boolean
	 */
	public static boolean testContains() {
		Attribute[] array = { Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT };
		BinaryBookshelf shelf = new BinaryBookshelf(array);
		Book.resetGenerator();
		Book book = new Book("Title", 506, "Smith", "John");
		shelf.insertBook(book);
		// tests if an added book is recognized in contains method
		if (!shelf.contains(book))
			return false;

		Book book1 = new Book("Chapter", 67, "Johnson", "Matt");

		// tests if a second book in a different position is recognized in contains
		// method
		if (shelf.contains(book1))
			return false;

		Book book2 = new Book("Basketball", 67, "Austin", "Austin");
		Book book3 = new Book("Destiny", 89, "Kalil", "Riley");

		shelf.getRoot().setLeft(new TreeNode<Book>(book1, new TreeNode<Book>(book2), new TreeNode<Book>(book3)));
		Book book4 = new Book("International", 89, "Yiannis", "Zack");
		shelf.getRoot().setRight(new TreeNode<Book>(book4));

		// tests if contains recognizes all books added using TreeNode methods
		if (!shelf.contains(book))
			return false;
		if (!shelf.contains(book1))
			return false;
		if (!shelf.contains(book2))
			return false;
		if (!shelf.contains(book3))
			return false;
		if (!shelf.contains(book4))
			return false;
		Book book5 = new Book("Child", 78, "Quam", "Mike");

		// makes sure it doesn't say a book is in the tree when that book is not in the
		// tree
		if (shelf.contains(book5))
			return false;
		return true;
	}

	/**
	 * Tests getBooksByAuthor() method
	 * 
	 * @return boolean
	 */
	public static boolean testGetBooksByAuthor() {
		Attribute[] array = { Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT };
		BinaryBookshelf shelf = new BinaryBookshelf(array);
		Book.resetGenerator();

		Book book = new Book("Title", 506, "Smith", "John");
		shelf.insertBook(book);
		ArrayList<Book> list = shelf.getBooksByAuthor(book.getAuthor());

		// tests if a book is added to the list with the right author name
		if (list.size() != 1)
			return false;
		if (list.get(0) != book)
			return false;

		// tests if the list doesn't add a book with the wrong author name
		list = shelf.getBooksByAuthor("Bourne, Jason");
		if (list.size() != 0)
			return false;

		Book book1 = new Book("Chapter", 67, "Johnson", "Matt");
		Book book2 = new Book("Basketball", 67, "Austin", "Austin");
		Book book3 = new Book("Destiny", 89, "Kalil", "Riley");
		Book book4 = new Book("International", 89, "Yiannis", "Zack");
		Book book5 = new Book("Skiing", 70, "Yiannis", "Zack");

		shelf.getRoot().setLeft(new TreeNode<Book>(book1, new TreeNode<Book>(book2), new TreeNode<Book>(book3)));
		shelf.getRoot().setRight(new TreeNode<Book>(book4, null, new TreeNode<Book>(book5)));
		
		list = shelf.getBooksByAuthor(book1.getAuthor());

		// tests if it gets books by an author with only one Book present in the
		// Bookshelf
		if (list.size() != 1)
			return false;
		if (list.get(0) != book1)
			return false;

		list = shelf.getBooksByAuthor(book4.getAuthor());

		// tests if it gets books by the author with multiple Books present in the
		// Bookshelf
		if (list.size() != 2)
			return false;
		if (list.get(0) != book4)
			return false;

		list = shelf.getBooksByAuthor("Bourne, Jason");

		// tests if it gets books by an author with no Books present in the Bookshelf
		if (list.size() != 0)
			return false;

		return true;
	}

	/**
	 * Runs all of the tests in this class and makes sure they all return true
	 * 
	 * @return boolean
	 */
	public static boolean runAllTests() {
		if (testTreeNode())
			if (testEmptyTree())
				if (testInsertBook())
					if (testContains())
						if (testGetBooksByAuthor())
							return true;
		return false;
	}

	/**
	 * Main method for the tester class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean value = runAllTests();
		System.out.println(value);
	}

}
