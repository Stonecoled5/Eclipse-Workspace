//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Tests Sokoban
// Files:           testSokoban.java, Config.java, Sokoban.java
// Course:          CS 200, fall term, 2018
//
// Author:          Cole Johnstone
// Email:           cjohnstone@wisc.edu 
// Lecturer's Name: Mark Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Naman Ladsaria
// Partner Email:   nladsaria@wisc.edu
// Lecturer's Name: Mark Renault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _X__ We have both read and understand the course Pair Programming Policy.
//   _X__ We have registered our team prior to the team registration deadline.
//

/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/** This file contains testing methods for the Sokoban project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban class as
 * they are developed. These methods are all private as they are only intended
 * for use within this class.
 * 
 * @author Marc Renault
 * @author Cole Johnstone
 * @author Naman Ladsaria
 */
public class TestSokoban {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 * 
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		// Milestone 1
		testCheckLevel();// calls testCheckLevel method
		// Milestone 2
		testInitBoard();// calls testInitBoard method
		testCheckWin();// calls testCheckWin method
		testCalcDelta();// calls testCalcDelta method
		testCheckDelta();// calls testCheckDelta method
		// Milestone 3
		testTogglePos();// calls testTogglePos method
		testShiftBox();// calls testShiftBox method
		testDoMove();// calls testDoMove method
		testProcessMove();// calls testProcessMove method
	}

	/*
	 * Tests the first four tests of checkLevel in Sokoban to see if they are
	 * working properly
	 * 
	 */
	private static void testCheckLevel() {// defines testCheckLevel method
		int numTests = 4;
		int passed = numTests;
		int res;
		// Test 1
		// checks if the method checkLevel correctly makes sure that lvl is greater than
		// or equal to 0
		if ((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
			System.out.println("FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
			passed--;
		}

		// Test 2
		// checks if the checkLevel method correctly makes sure that lvl is valid in the
		// given set of boards
		char[][][] lvl = new char[2][][];
		if ((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
			System.out.println("FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
			passed--;
		}
		int[][] gol = new int[0][];

		// Test 3
		// checks if checkLevel correctly makes sure that the lvl is a valid index in
		// the goals array
		if ((res = Sokoban.checkLevel(1, Config.LEVELS, gol)) != -2) {
			System.out.println("FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + res);
			passed--;
		}
		char[][][] lvl3 = new char[5][5][5];
		// test 4
		// checks if checkLevel correctly makes sure that the number of box characters
		// is greater than 0
		if ((res = Sokoban.checkLevel(1, lvl3, Config.GOALS)) != -3) {
			System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned " + res);
			passed--;
		}

		System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
	}

	/**
	 * Returns true if the arrays are the same size and have the same contents.
	 */
	private static boolean compBoards(char[][] a, char[][] b) {// defines compBoards method
		if (a == null || b == null)// makes sure there are contents in the two arrays
			return false;
		if (a.length != b.length)// makes sure that the arrays have the same length
			return false;
		for (int i = 0; i < a.length; i++) {// makes sure that the contents of the arrays equal each other
			if (!Arrays.equals(a[i], b[i])) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Tests the initBoard method to make sure that it correctly creates a 2d array
	 * the same as the 3d array going in
	 */
	private static void testInitBoard() {// defines testInitBoard method
		int numTests = 4;
		int passed = numTests;

		// Test 1
		int[] pTest1 = new int[2];
		char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);// calls initBoard method
		if (!Arrays.equals(pTest1, new int[] { 4, 4 })) {// checks if the worker position updated correctly
			System.out.println(
					"FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
							+ Arrays.toString(pTest1));
			passed--;
		}
		char[][] bCompTest1 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if (!compBoards(bTest1, bCompTest1)) {// checks if the 2d array was correctly created
			System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
			System.out.println("Generated:");
			Sokoban.printBoard(bTest1);
			System.out.println("Expected:");
			Sokoban.printBoard(bCompTest1);
			passed--;
		}
		// End of Test 1
		int[] pTest2 = new int[2];
		char[][] bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest2);// calls initBoard method
		if (!Arrays.equals(pTest2, new int[] { 7, 10 })) {// checks if the worker position updated correctly
			System.out.println(
					"FAILED: Sokoban.initBoard Test 2. Expected initial position: {8, 11} , but value after call "
							+ Arrays.toString(pTest2));
			passed--;
		}
		char[][] bCompTest2 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };
		if (!compBoards(bTest2, bCompTest2)) {// checks if the 2d array was correctly created
			System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
			System.out.println("Generated:");
			Sokoban.printBoard(bTest2);
			System.out.println("Expected:");
			Sokoban.printBoard(bCompTest2);
			passed--;
		}

		System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
	}

	/*
	 * checks if the checkWin method is correctly making sure there are no more
	 * boxes left on the board
	 */
	private static void testCheckWin() {
		char[][] board = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR }, };
		boolean t = Sokoban.checkWin(board);
		if (t == true) {// checks if the checkWin method correctly determines that there are still boxes
						// on the board
			System.out.println("Failed");
		} else {
			System.out.println("Passed");
		}
	}

	/*
	 * checks if the calcDelta method correctly changes the input of the user to the
	 * delta value of that input
	 */
	private static void testCalcDelta() {
		String str = "81";
		int[] answer = new int[2];
		answer[0] = -1;
		answer[1] = 0;
		int[] t = Sokoban.calcDelta(str);// calls the calcDelta method with input of string
		if (!Arrays.equals(t, answer)) {// checks if what calcDelta returned is the correct value
			System.out.println(Arrays.toString(t));
		} else {
			System.out.println("Passed");
		}
	}

	/*
	 * checks if the checkDelta method correctly determines if the delta value is
	 * valid. The test checks if the method correctly determines that the move of
	 * the worker would be outside of the wall/array
	 */
	private static void testCheckDelta() {
		char[][] board = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR }, };
		int[] pos = { 4, 2 };
		int[] delta = { 1, 0 };
		char[] valid = { Config.WORKER_CHAR, Config.WORK_GOAL_CHAR };
		if (Sokoban.checkDelta(board, pos, delta, valid) != -4) {// checks if the method identifies the worker would be
																	// outside the array
			System.out.println("Failed");
		} else {
			System.out.println("Passed");
		}
	}

	/*
	 * checks if togglePos method correctly changes the character at the new
	 * position to the correct character
	 */
	private static void testTogglePos() {
		char[][] board = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR }, };
		int[] pos = { 2, 2 };
		Sokoban.togglePos(board, pos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);// implements
																									// togglePos method
																									// in Sokoban to
																									// change the
																									// GOAL_CHAR into
																									// WORK_GOAL_CHAR
		if (board[2][2] == Config.WORK_GOAL_CHAR) {// checks if the togglePos correctly changed the value of GOAL_CHAR
													// to WORK_GOAL_CHAR
			System.out.println("Passed");
		}
		;
	}

	/*
	 * checks if the shiftBox method correctly moves the box
	 */

	private static void testShiftBox() {
		char[][] board = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR }, };

		char[][] board2 = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR }, };
		int[] pos = { 2, 3 };
		int[] delta = { -1, 0 };
		int t = Sokoban.shiftBox(board, pos, delta);// calls shiftBox method in Sokoban
		if (t == 1) {// checks if the checkDelta in the shiftBox passed
			if (board[1][3] == Config.BOX_CHAR) {// checks if the box was moved
				if (board[2][3] == Config.EMPTY_CHAR) {// checks if the space the box was in is now empty
					System.out.println("Passed");
				} else {
					System.out.println("Failed1");
				}
			} else {
				System.out.println("Failed2");
			}
		}

		else {
			System.out.print(t);
		}
	}

	/*
	 * checks if the doMove method correctly moves the worker character and replaces
	 * its old position with an empty character
	 */

	private static void testDoMove() {
		char[][] board = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR }, };

		char[][] board2 = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR }, };
		int[] pos = { 4, 4 };
		int[] delta = { 0, -3 };
		int t = Sokoban.doMove(board, pos, delta);// calls the doMove method and makes it equal to the value of t
		if (t == 1) {// checks if the checkDelta method in the doMove method correctly returns 1
			if (board[4][4] == Config.EMPTY_CHAR) {// checks if the old position is now an empty character
				if (board[4][1] == Config.WORKER_CHAR) {// checks if the new position is changed to a worker
					System.out.println("Passed");
				} else {
					System.out.println("Failed");
				}
			} else {
				System.out.println("Failed");

			}
		} else {
			System.out.println(t);
		}
	}
	/*
	 * checks if the processMove method correctly determines if the delta is {0,0},
	 * calls the doMove method the number of times based on the delta and then
	 * returns 1
	 */

	private static void testProcessMove() {
		char[][] board = {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR }, };
		int[] pos = { 4, 4 };
		int[] delta = { 0, 0 };
		int t = Sokoban.processMove(board, pos, delta);// calls the processMove method
		if (t != 0) {// checks if the value returned from the method identified that the delta was
						// {0,0}
			System.out.println(t);
		} else {
			System.out.println("Passed");
		}
		int[] pos2 = { 4, 4 };
		int[] delta2 = { 0, -3 };
		int u = Sokoban.processMove(board, pos2, delta2);// calls the processMove method
		if (u != 1) {// checks if the value returned from the method was the correct value from the
						// doMove method
			System.out.println(u);
		} else {
			System.out.println("Passed");

		}

	}
}