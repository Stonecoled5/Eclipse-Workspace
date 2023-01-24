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
/**
 * A generic binary tree node
 * @author Cole Johnstone
 *
 */
public class TreeNode<T> {
	private T data; // The data contained in this node
	private TreeNode<T> left;// The left child of this node
	private TreeNode<T> right;// The right child of this node

	/**
	 * Constructs a node with the given data and no children
	 * 
	 * @param data
	 */
	public TreeNode(T data) {
		this.data = data;
		left = null;
		right = null;
	}

	/**
	 * Constructs a node with the given data and children
	 * 
	 * @param data
	 * @param left
	 * @param right
	 */
	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * Accessor for the data contained in the node
	 * 
	 * @return data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Accessor for the left child of the node
	 * 
	 * @return left
	 */
	public TreeNode<T> getLeft() {
		return left;
	}

	/**
	 * Accessor for the right child of the node
	 * 
	 * @return right
	 */
	public TreeNode<T> getRight() {
		return right;
	}

	/**
	 * Change the left child reference of this node; may be null
	 * 
	 * @param left
	 */
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	/**
	 * Change the right child reference of this node; may be null
	 * 
	 * @param right
	 */
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	/**
	 * Returns a string representation of this node's data
	 * 
	 * @return String
	 */
	public String toString() {
		return data.toString();
	}
}
