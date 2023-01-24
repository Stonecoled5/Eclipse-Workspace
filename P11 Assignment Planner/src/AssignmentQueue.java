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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Assignments.
 * Guarantees the min-heap invariant, so that the Assignment at the root should
 * have the earliest due date, and children always have a due date after or at
 * the same time as their parent. The root of a non-empty queue is always at
 * index 0 of this array-heap.
 */
public class AssignmentQueue extends Object implements PriorityQueueADT<Assignment>, Iterable<Assignment> {
	private Assignment[] queue; // array min-heap of assignments representing this priority queue
	private int size; // size of this priority queue

	/**
	 * Creates a new empty AssignmentQueue with the given capacity
	 * 
	 * @param capacity Capacity of this AssignmentQueue
	 * @throws IllegalArgumentException with a descriptive error message if the
	 *                                  capacity is not a positive integer
	 */
	public AssignmentQueue(int capacity) throws IllegalArgumentException {
		if (capacity < 1)
			throw new IllegalArgumentException("Capacity is not a positive integer");
		size = 0;
		queue = new Assignment[capacity];
	}

	/**
	 * Checks whether this AssignmentQueue is empty
	 * 
	 * @return {@code true} if this AssignmentQueue is empty
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Returns the size of this AssignmentQueue
	 * 
	 * @return the size of this AssignmentQueue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the capacity of this AssignmentQueue
	 * 
	 * @return the capacity of this AssignmentQueue
	 */
	public int capacity() {
		return queue.length;
	}

	/**
	 * Removes all elements from this AssignmentQueue
	 */
	@Override
	public void clear() {
		queue = new Assignment[queue.length];
		size = 0;
	}

	/**
	 * Returns the Assignment at the root of this AssignmentQueue, i.e. the
	 * Assignment with the earliest due date.
	 * 
	 * @return the Assignment in this AssignmentQueue with the earliest due date
	 * @throws NoSuchElementException if this AssignmentQueue is empty
	 */
	@Override
	public Assignment peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty!");
		return queue[0];
	}

	/**
	 * Adds the given Assignment to this AssignmentQueue at the correct position
	 * based on the min-heap ordering. This queue should maintain the min-heap
	 * invariant, so that the Assignment at each index has an earlier or equivalent
	 * due-date than the Assignments in its child nodes. Assignments should be
	 * compared using the Assignment.compareTo() method.
	 * 
	 * @param e Assignment to add to this AssignmentQueue
	 * @throws NullPointerException  if the given Assignment is null
	 * @throws IllegalStateException with a descriptive error message if this
	 *                               AssignmentQueue is full
	 */
	@Override
	public void enqueue(Assignment e) throws NullPointerException, IllegalStateException {
		if (e == null)
			throw new NullPointerException("Assignment is null");// empty parameter
		if (size == queue.length)
			throw new IllegalStateException("Queue full");// reached capacity
		queue[size] = e;
		size++;
		percolateUp(size - 1);// put assignment in correct order in the hierarchy
	}

	/**
	 * Removes and returns the Assignment at the root of this AssignmentQueue, i.e.
	 * the Assignment with the earliest due date.
	 * 
	 * @return the Assignment in this AssignmentQueue with the earliest due date
	 * @throws NoSuchElementException with a descriptive error message if this
	 *                                AssignmentQueue is empty
	 */
	@Override
	public Assignment dequeue() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty!");
		Assignment removed = queue[0];
		if (size == 1) { // only one element in queue
			size--;
			queue[0] = null;
			return removed;
		}
		queue[0] = queue[size - 1];
		queue[size - 1] = null;
		size--;
		percolateDown(0);// put assignment in correct order in the hierarchy
		return removed;
	}

	/**
	 * Recursive implementation of percolateDown() method. Restores the min-heap
	 * invariant of a given subtree by percolating its root down the tree. If the
	 * element at the given index does not violate the min-heap invariant (it is due
	 * before its children), then this method does not modify the heap. Otherwise,
	 * if there is a heap violation, then swap the element with the correct child
	 * and continue percolating the element down the heap.
	 * 
	 * @param i index of the element in the heap to percolate downwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch
	 *                                   the exception
	 */
	protected void percolateDown(int i) {
		// Time complexity: O(logN)
		try {
			if (i < 0 || i >= size)
				throw new IndexOutOfBoundsException("index is out of bounds");
			if (queue[(2 * i) + 1] == null && queue[(2 * i) + 2] == null)
				return;// no children
			int minChild = 0; // index of smallest child
			if (queue[(2 * i) + 1] == null && queue[(2 * i) + 2] != null) {// only left child is available
				minChild = (2 * i) + 2;
			}
			if (queue[(2 * i) + 1] != null && queue[(2 * i) + 2] == null) {// only right child is available
				minChild = (2 * i) + 1;
			}
			if (queue[(2 * i) + 1] != null && queue[(2 * i) + 2] != null) {// has a child on both left and right
				// both children are larger than parent percolating down is done;
				if (queue[i].compareTo(queue[(2 * i) + 1]) < 0 && queue[i].compareTo(queue[(2 * i) + 2]) < 0)
					return;
				// gets the smallest child out of the two children
				int compareValue = queue[(2 * i) + 1].compareTo(queue[(2 * i) + 2]);
				if (compareValue == 0)
					minChild = (2 * i) + 1;
				if (compareValue > 0)
					minChild = (2 * i) + 2;
				if (compareValue < 0)
					minChild = (2 * i) + 1;
			}
			// compares the smallest child with the parent to make sure parent is smaller
			// than
			// smallest child
			if (queue[i].compareTo(queue[minChild]) > 0) {
				Assignment swap = queue[i];
				queue[i] = queue[minChild];
				queue[minChild] = swap;
				percolateDown(minChild);
				return;
			}
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

	/**
	 * Recursive implementation of percolateUp() method. Restores the min-heap
	 * invariant of the tree by percolating a leaf up the tree. If the element at
	 * the given index does not violate the min-heap invariant (it occurs after its
	 * parent), then this method does not modify the heap. Otherwise, if there is a
	 * heap violation, swap the element with its parent and continue percolating the
	 * element up the heap.
	 * 
	 * @param i index of the element in the heap to percolate upwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch
	 *                                   the exception
	 */
	protected void percolateUp(int i) {
		// Time complexity: O(logN)
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException("index is out of bounds");
		try {
			if (i == 0)
				return;// at root can't go higher
			if (queue[i].compareTo(queue[(i - 1) / 2]) < 0) {// if child is smaller than parent,
				Assignment swap = queue[(i - 1) / 2]; // swap the two and continue comparing
				queue[(i - 1) / 2] = queue[i];
				queue[i] = swap;
				percolateUp((i - 1) / 2);
			}
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

	/**
	 * Returns a deep copy of this AssignmentQueue containing all of its elements in
	 * the same order. This method does not return the deepest copy, meaning that
	 * you do not need to duplicate assignments. Only the instance of the heap
	 * (including the array and its size) will be duplicated.
	 * 
	 * @return a deep copy of this AssignmentQueue. The returned new assignment
	 *         queue has the same length and size as this queue.
	 */
	public AssignmentQueue deepCopy() {
		AssignmentQueue queue2 = new AssignmentQueue(queue.length);
		// creates a new queue and copies all assignments over to the new queue in order
		for (int i = 0; i < queue.length; i++)
			queue2.queue[i] = queue[i];
		queue2.size = size();
		return queue2;
	}

	/**
	 * Returns a String representing this AssignmentQueue, where each element
	 * (assignment) of the queue is listed on a separate line, in order from
	 * earliest to latest.
	 * 
	 * @see Assignment#toString()
	 * @see AssignmentIterator
	 * @return a String representing this AssignmentQueue
	 */
	@Override
	public String toString() {
		StringBuilder val = new StringBuilder();
		for (int i = 0; i < size; i++) {
			val.append(queue[i]).append("\n");
		}
		return val.toString();
	}

	/**
	 * Returns an Iterator for this AssignmentQueue which proceeds from the earliest
	 * to the latest Assignment in the queue.
	 * 
	 * @see AssignmentIterator
	 * @return an Iterator for this AssignmentQueue
	 */
	@Override
	public Iterator<Assignment> iterator() {
		return new AssignmentIterator(this);
	}
}
