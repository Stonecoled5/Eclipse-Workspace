
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P11 Assignment Planner
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

import java.util.NoSuchElementException;

/**
 * Tester class for the AssignmentQueue implementation of PriorityQueueADT
 */
public class AssignmentQueueTester {
	/**
	 * Tests the functionality of the constructor for AssignmentQueue
	 *
	 * @return true if the constructor of AssignmentQueue functions properly
	 * @see AssignmentQueue#AssignmentQueue(int)
	 */
	public static boolean testConstructor() {
		try {
			new AssignmentQueue(-1); // invalid capacity should throw exception
		} catch (IllegalArgumentException e) {
			try {
				// makes sure the empty queue is empty and the size is 0
				AssignmentQueue queue = new AssignmentQueue(4);
				if (queue.isEmpty())
					if (queue.size() == 0)
						return true;
			} catch (Exception e1) {
				return false;
			}
		} catch (Exception e2) {
			return false;
		}
		return false;
	}

	/**
	 * Tests the functionality of the enqueue() and peek() methods
	 *
	 * @return true if AssignmentQueue.enqueue() and AssignmentQueue.peek() function
	 *         properly
	 */
	public static boolean testEnqueue() {
		AssignmentQueue queue = new AssignmentQueue(5);
		boolean value = false;
		try {
			// Calling peek on an empty queue should throw a NoSuchElementException
			queue.peek();
		} catch (NoSuchElementException e) {
			try {
				// Calling enqueue on a queue which is empty should add the Assignment, and
				// increment the size
				// of the queue
				Assignment assignment = new Assignment("Blah", 12, 5, 20);
				queue.enqueue(assignment);
				if (!queue.peek().equals(assignment))
					return false;
				if (queue.size() != 1)
					return false;
				// Calling enqueue on a non-empty queue should add the Assignment at the proper
				// position, and increment the size of the queue
				Assignment assignment1 = new Assignment("HAHA", 12, 3, 10);
				queue.enqueue(assignment1);
				if (!queue.peek().equals(assignment1))
					return false;
				if (queue.size() != 2)
					return false;
				// Calling peek on a non-empty queue should always return the Assignment with
				// the earliest due date
				Assignment assignment2 = new Assignment("Zach", 12, 7, 10);
				queue.enqueue(assignment2);
				if (!queue.peek().equals(assignment1))
					return false;
				if (queue.size() != 3)
					return false;

				Assignment assignment3 = new Assignment("Pop", 12, 1, 10);
				queue.enqueue(assignment3);
				if (!queue.peek().equals(assignment3))
					return false;
				if (queue.size() != 4)
					return false;

				Assignment assignment4 = new Assignment("Kachow", 10, 8, 10);
				queue.enqueue(assignment4);
				if (!queue.peek().equals(assignment4))
					return false;
				if (queue.size() != 5)
					return false;

				value = true;
				// Calling enqueue on a full queue should throw an IllegalStateException
				Assignment assignment5 = new Assignment("Kachow", 10, 8, 10);
				queue.enqueue(assignment5);
				return false;
			} catch (IllegalStateException e3) {
				if (value) {
					try {
						// Calling enqueue with a null Assignment should throw a NullPointerException
						Assignment assignment = null;
						queue.enqueue(assignment);
					} catch (NullPointerException e4) {
						return true;
					} catch (Exception e5) {
						return false;
					}
				} else
					return false;
			} catch (Exception e2) {
				return false;
			}
		} catch (Exception e1) {
			return false;
		}
		return false;
	}

	/**
	 * Tests the functionality of dequeue() and peek() methods.
	 * 
	 * @return true if AssignmentQueue.dequeue() and AssignmentQueue.peek() function
	 *         properly
	 */
	public static boolean testDequeuePeek() {
		AssignmentQueue queue = new AssignmentQueue(6);
		boolean value = false;
		try {
			Assignment assignment = new Assignment("Blah", 12, 5, 20);
			queue.enqueue(assignment);
			Assignment assignment1 = new Assignment("HAHA", 12, 3, 10);
			queue.enqueue(assignment1);
			// The peek() method must return without removing the assignment with the
			// highest priority in the queue
			if (!queue.peek().equals(assignment1))
				return false;
			if (!queue.peek().equals(assignment1))
				return false;
			if (queue.size() != 2)
				return false;
			// The dequeue() method must remove, and return the assignment with the highest
			// priority in the queue
			if (!queue.dequeue().equals(assignment1))
				return false;
			// The size must be decremented by one, each time the dequeue() method is
			// successfully called
			if (queue.size() != 1)
				return false;
			if (!queue.peek().equals(assignment))
				return false;
			// calling dequeue on a queue of size one
			if (!queue.dequeue().equals(assignment))
				return false;
			if (queue.size() != 0)
				return false;
			try {
				// calling peek on an empty queue
				queue.peek();
			} catch (NoSuchElementException e1) {
				try {
					// calling dequeue on an empty queue
					queue.dequeue();
				} catch (NoSuchElementException e3) {
					value = true;
				} catch (Exception e4) {
					return false;
				}

			} catch (Exception e2) {
				return false;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} finally {
			if (value) {
				// tests if dequeue works on a queue with at least 6 books
				Assignment assignment = new Assignment("Blah", 12, 5, 20);
				queue.enqueue(assignment);
				Assignment assignment1 = new Assignment("HAHA", 12, 3, 10);
				queue.enqueue(assignment1);
				Assignment assignment2 = new Assignment("Zach", 12, 7, 10);
				queue.enqueue(assignment2);
				Assignment assignment3 = new Assignment("Pop", 12, 1, 10);
				queue.enqueue(assignment3);
				Assignment assignment4 = new Assignment("Kachow", 10, 8, 10);
				queue.enqueue(assignment4);
				Assignment assignment5 = new Assignment("Kerplunk", 11, 9, 10);
				queue.enqueue(assignment5);
				if (!queue.dequeue().equals(assignment4))
					return false;
				if (queue.size() != 5)
					return false;
				if (!queue.dequeue().equals(assignment5))
					return false;
				if (queue.size() != 4)
					return false;
				if (!queue.dequeue().equals(assignment3))
					return false;
				if (queue.size() != 3)
					return false;
				if (!queue.dequeue().equals(assignment1))
					return false;
				if (queue.size() != 2)
					return false;
				if (!queue.dequeue().equals(assignment))
					return false;
				if (queue.size() != 1)
					return false;
				if (!queue.dequeue().equals(assignment2))
					return false;
				if (queue.size() != 0)
					return false;
				return true;
			}
		}
	}

	/**
	 * Tests the functionality of the clear() method Should implement at least the
	 * following scenarios: - clear can be called on an empty queue with no errors -
	 * clear can be called on a non-empty queue with no errors - After calling
	 * clear, the queue should contain no Assignments
	 *
	 * @return true if AssignmentQueue.clear() functions properly
	 */
	public static boolean testClear() {
		try {
			AssignmentQueue queue = new AssignmentQueue(5);
			// clear on an empty queue should not have an error
			queue.clear();
			Assignment assignment = new Assignment("Blah", 12, 5, 20);
			queue.enqueue(assignment);
			Assignment assignment1 = new Assignment("HAHA", 12, 3, 10);
			queue.enqueue(assignment1);
			// clear can be called on a non-empty queue with no errors
			// After calling clear, the queue should contain no Assignments
			queue.clear();
			if (queue.size() != 0)
				return false;
			try {
				queue.peek();
			} catch (NoSuchElementException e1) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	/**
	 * Tests the functionality of the deepCopy() method
	 * 
	 * @return boolean
	 */
	public static boolean testDeepCopy() {
		AssignmentQueue queue = new AssignmentQueue(6);
		Assignment assignment = new Assignment("Blah", 12, 5, 20);
		queue.enqueue(assignment);
		Assignment assignment1 = new Assignment("HAHA", 12, 3, 10);
		queue.enqueue(assignment1);
		Assignment assignment2 = new Assignment("Zach", 12, 7, 10);
		queue.enqueue(assignment2);
		Assignment assignment3 = new Assignment("Pop", 12, 1, 10);
		queue.enqueue(assignment3);
		Assignment assignment4 = new Assignment("Kachow", 10, 8, 10);
		queue.enqueue(assignment4);
		Assignment assignment5 = new Assignment("Kerplunk", 11, 9, 10);
		queue.enqueue(assignment5);
		AssignmentQueue queueNew = queue.deepCopy();
		// tests if the new queue isn't a shallow copy
		if (queueNew == queue)
			return false;
		// tests if the size of the queues are the same
		if (queueNew.size() != queue.size())
			return false;
		// tests if the contents of the queues are the same
		for (int i = 0; i < queue.size(); i++) {
			if (!queueNew.dequeue().equals(queue.dequeue()))
				return false;
			if (queueNew.size() != queue.size())
				return false;
		}
		return true;
	}

	/**
	 * Tests all the methods of the AssignmentQueue class
	 * 
	 */
	public static boolean runAllTests() {
		if (testConstructor())
			if (testEnqueue())
				if (testDequeuePeek())
					if (testClear())
						if (testDeepCopy())
							return true;
		return false;
	}

	/**
	 * Main method
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		System.out.println(runAllTests());
	}
}
