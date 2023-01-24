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
 * Defines a LinkedNode
 * 
 * @author Cole Johnstone
 *
 */
public class LinkedNode<T> {
	private T data; // indicates the value in the node
	private LinkedNode<T> next; // indicates the next node in the list

	/**
	 * initializes the data field and leaves next null
	 * 
	 * @param data
	 */
	public LinkedNode(T data) {
		this.data = data;
		next = null;
	}

	/**
	 * initializes both data and next fields
	 * 
	 * @param data
	 * @param next
	 */
	public LinkedNode(T data, LinkedNode<T> next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * returns a reference to the next node in the list
	 * 
	 * @return next Node
	 */
	public LinkedNode<T> getNext() {
		return next;
	}

	/**
	 * returns the value of the data instance field
	 * 
	 * @return data
	 */
	public T getData() {
		return data;
	}

	/**
	 * returns the String representation of the node’s data
	 */
	public String toString() {
		if (data == null) {
			return "null";
		}
		return data.toString();
	}

	/**
	 * updates the next field to be the provided node (possibly null); does not
	 * return a value
	 * 
	 * @param next
	 */
	public void setNext(LinkedNode<T> next) {
		this.next = next;
	}
}
