//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Sokoban game
// Files:            Config.java, Sokoban.java
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

import java.util.Scanner;
import java.util.Random;

public class Sokoban {

	public static int promptInt(Scanner sc, String prompt, int min, int max) {// defines the promptint method

		int level = 0;
		boolean done = true;
		System.out.print(prompt);
		while (done) { // To make sure the input is valid level
			if (sc.hasNextInt()) {// checks if it is an int
				level = sc.nextInt();
				sc.nextLine();
				if ((level >= 0) && (level <= max)) {// checks if it is in between the valid levels
					System.out.println("Sokoban Level " + level);
					done = false;
				} else if (level == -1) {// If level is -1 goes back to main method to choose random level
					done = false;
				} else {
					System.out.println("Invalid value.");
					System.out.print(prompt);
					done = true;
				}
			} else {
				System.out.println("Invalid value.");
				System.out.print(prompt);
				sc.nextLine();
				done = true;
			}
		}

		return level;
	}

	public static char promptChar(Scanner sc, String prompt) {// defines promptchar method
		System.out.print(prompt);
		char val = '\0';
		String goop = "";
		goop = sc.nextLine();
		if (goop.equals("")) {// checks if there is an input
			return '\u0000';
		}
		val = goop.charAt(0);
		val = java.lang.Character.toLowerCase(val);// changes the input to lowercase

		return val;
	}

	/**
	 * Prompts the user for a string value by displaying prompt. Note: This method
	 * should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input,
	 * remove any leading and trailing whitespace, and return the input converted to
	 * lower case.
	 *
	 * @param sc     The Scanner instance to read from System.in
	 * @param prompt The user prompt.
	 * @return Returns the string entered by the user, converted to lower case with
	 *         leading and trailing whitespace removed.
	 */
	public static String promptString(Scanner sc, String prompt) {// implements prompt string
		String val = null;

		System.out.print(prompt);
		val = sc.nextLine();// gets one line of input
		val = val.trim().toLowerCase();// deletes whitespaces in the front and the end of the string
										// and changes to lowercase

		return val;

	}

	/**
	 * Initializes the game board to a given level. You can assume that the level at
	 * lvl has been successfully verified by the checkLevel method and that pos is
	 * an array of length 2.
	 *
	 * 1 - The game board should be created row-by-row. a - For each row, copy the
	 * values from the corresponding row in the 2-d array contained at index lvl in
	 * levels. b - When the worker is located, it's position should be recorded in
	 * the pos parameter. 2 - For each goal described in the array at index lvl of
	 * goals, convert the character at the goal coordinate to: -
	 * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if it
	 * contains a box - Config.GOAL_CHAR otherwise
	 * 
	 * @param lvl    The index of the level to load.
	 * @param levels The array containing the levels.
	 * @param goals  The parallel array to levels, containing the goals for the
	 *               levels.
	 * @param pos    The starting pos of the worker. A length 2 array, where index 0
	 *               is the row and index 1 is the column.
	 * @return A two dimension array representing the initial configuration for the
	 *         given level.
	 */
	public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {// implements
																							// the
																							// initboard

		char[][] levels2 = new char[levels[lvl].length][];// defining a new array
		for (int i = 0; i < levels[lvl].length; i++) {
			char[] lvlr1 = new char[levels[lvl][i].length];
			levels2[i] = lvlr1;
			for (int j = 0; j < levels[lvl][i].length; j++) {
				levels2[i][j] = levels[lvl][i][j];// Converts 3d array to 2d
				if (levels[lvl][i][j] == Config.WORKER_CHAR) {// updates the position of the worker
					pos[0] = i;
					pos[1] = j;
				}
			}
		}
		for (int r = 0; r < goals[lvl].length; r = r + 2) {// puts the Goal characters in the
															// correct position on the board
			int row = goals[lvl][r];
			int col = goals[lvl][r + 1];
			if (levels2[row][col] == Config.WORKER_CHAR) {
				levels2[row][col] = Config.WORK_GOAL_CHAR;
			}
			if (levels2[row][col] == Config.BOX_CHAR) {
				levels2[row][col] = Config.BOX_GOAL_CHAR;
			} else {
				levels2[row][col] = Config.GOAL_CHAR;
			}

		}
		return levels2;
	}

	/**
	 * Prints out the game board.
	 * 
	 * 1 - Since the game board does not contain the outer walls, print out a
	 * sequence of Config.WALL_CHAR with a length equal to that of the first row of
	 * board, plus the outer wall to the left and the right. 2 - For each row in
	 * board, print out a Config.WALL_CHAR, followed by the contents of the row,
	 * followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence of
	 * Config.WALL_CHAR with a length equal to that of the last row of board, plus
	 * the outer wall to the left and the right.
	 *
	 * Note: each row printed out should be terminated by a new line.
	 *
	 * @param board The board to print.
	 */
	public static void printBoard(char[][] board) {// Implements printBoard
		int p = 0;

		for (int i = 0; i <= board[0].length + 1; i++) {// Prints out the upper outer wall
			System.out.print(Config.WALL_CHAR);
		}
		System.out.println();
		for (int j = 0; j < board.length; j++) {// prints out the contents of the level and the wall
												// char at the edges
			System.out.print(Config.WALL_CHAR);
			for (int i = 0; i < board[j].length; i++) {
				System.out.print(board[j][i]);
			}
			System.out.println(Config.WALL_CHAR);
		}

		for (int i = 0; i < board.length; i++) {// Finds the longest row
			if (board[i].length > p) {
				p = board[i].length + 1;
			}
		}
		for (int i = 0; i <= p; i++) {// prints the lower outer wall
			System.out.print(Config.WALL_CHAR);
		}
		System.out.println();
	}

	/**
	 * Runs a given level through some basic sanity checks.
	 *
	 * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl is
	 * a valid index in levels, that the 2-d array at index lvl exists and that it
	 * contains at least 1 row. 3 - lvl is a valid index in goals, the 1-d array at
	 * index lvl exists and that it contains an even number of cells. 4 - the number
	 * of boxes is more than 0. 5 - the number of boxes equals the number of goals.
	 * 6 - the coordinate of each goal is valid for the given lvl and does not
	 * correspond to a wall cell. 7 - the number of workers is exactly 1. 8 - check
	 * for duplicate goals.
	 *
	 * @param lvl    The index of the level to load.
	 * @param levels The array containing the levels.
	 * @param goals  The parallel array to levels, containing the goals for the
	 *               levels.
	 * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test 2
	 *         fails: -1 - Test 3 fails: -2 - Test 4 fails: -3 - Test 5 fails: -4 -
	 *         Test 6 fails: -5 - Test 7 fails: -6 - Test 8 fails: -7
	 * 
	 */
	public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {// Implements
																				// CheckLevel
		// test1
		// checks if lvl >= 0
		if (lvl >= 0) {
		} else {
			return 0;
		}
		// test2
		// Checks if lvl is a valid index in levels, that the 2-d array at index lvl
		// exists.
		if (levels[lvl] != null && levels.length > lvl) {
			if (levels[lvl].length >= 1) {// Checks if the lvl contains at least 1 row
			}
		}

		else {
			return -1;
		}
		// test3
		// Checks if lvl is a valid index in goals
		if (goals.length > lvl) {
			if (goals[lvl].length % 2 == 0) {// Checks if the 1-d array at index lvl exists and that
												// it contains an even number of cells.
			}
		}

		else {
			return -2;
		}
		// test 4
		// Checks if the number of boxes is more than 0.
		int bux = 0;
		for (int g = 0; g < levels[lvl].length - 1; ++g) {
			for (int p = 0; p < levels[lvl][g].length - 1; ++p) {
				if (levels[lvl][g][p] == Config.BOX_CHAR) {
					bux++;
				}
			}
		}
		if (bux < 1) {
			return -3;
		}
		// test5
		// Checks if the number of boxes equals the number of goals.
		int num = goals[lvl].length;
		num = num / 2;
		int box = 0;
		for (int k = 0; k <= levels[lvl].length - 1; k++) {
			for (int l = 0; l <= levels[lvl][k].length - 1; l++) {
				if (levels[lvl][k][l] == Config.BOX_CHAR) {
					box = box + 1;
				}

			}
		}
		if (box != num) {
			return -4;
		}
		// test 6
		// he coordinate of each goal is valid for the given lvl and does not correspond
		// to a wall
		// cell.

		for (int r = 0; r <= goals[lvl].length - 1; r = r + 2) {
			int row = goals[lvl][r];
			int col = goals[lvl][r + 1];
			if (row < 0) {
				return -5;
			}
			if (row > levels[lvl].length - 1) {
				return -5;
			}
			if (col < 0) {
				return -5;
			}
			if (col > levels[lvl][row].length - 1) {
				return -5;
			} // CHecks if does not correspond to a wall cell.
			if (levels[lvl][row][col] == Config.WALL_CHAR) {
				return -5;
			}
		}

		// Test 7
		// Checks if the number of workers is exactly 1.
		int buf = 0;
		for (int u = 0; u <= levels[lvl].length - 1; ++u) {
			for (int i = 0; i <= levels[lvl][u].length - 1; ++i) {
				if (levels[lvl][u][i] == Config.WORKER_CHAR) {
					buf++;
				}
			}
		}
		if (buf != 1) {
			return -6;
		}

		// Test 8 -- Add in comments to explain the code

		for (int i = 0; i < goals[lvl].length - 1; i += 2) {// checks if the last value of the array goals with index
															// lvl is greater than i
			for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {// checks if the last value of the array goals with
																	// index lvl is greater than i plus2
				if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) { // checks if the array
																								// goals with index lvl
																								// and
																								// i is equal to the
																								// array goals with
																								// index lvl
																								// and j
					return -7;
				}
			}
		}
		return 1;
	}

	/**
	 * This method builds an int array with 2 cells, representing a movement vector,
	 * based on the String parameter.
	 *
	 * The rules to create the length 2 int array are as follows: - The 1st
	 * character of the String represents the direction. - The remaining characters
	 * (if there are any) are interpreted as integer and represent the magnitude or
	 * the number of steps to take.
	 *
	 * The cell at index 0 represents movement in the rows. Hence, a negative value
	 * represents moving up the rows and a positive value represents moving down the
	 * rows.
	 *
	 * The cell at index 1 represents movement in the columns. Hence, a negative
	 * value represents moving left in the columns and a positive value represents
	 * moving right in the columns.
	 *
	 * If the first character of moveStr does not match on of Config.UP_CHAR,
	 * Config.DOWN_CHAR, Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an
	 * array with 0 in both cells.
	 *
	 * If there are no characters after the first character of moveStr or the
	 * characters cannot be interpreted as an int, set the magnitude of the movement
	 * to 1.
	 *
	 * Hint: Use Scanner to parse the magnitude.
	 *
	 * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would
	 * represent moving up by one character. - If the parameter moveStr is "65": An
	 * array {0, 5} would represent moving right by 5 characters.
	 *
	 * @param moveStr The string to parse.
	 * @return The calculated movement vector as a 2 cell int array.
	 */
	public static int[] calcDelta(String moveStr) {// Implements CalcDelta
		int i;// Direction
		int x = 0;
		int y = 0;
		if (moveStr.length() < 1) {// if input length is less than 1 then puts the value {0,0}
			int[] calcDelta = { 0, 0 };
			return calcDelta;
		}
		i = moveStr.charAt(0);// First char
		Scanner scan = new Scanner(moveStr.substring(1));// All char except the first
		int magnitude = 1;
		if (scan.hasNextInt()) {// checks if there is a int
			magnitude = scan.nextInt();
		} else {
			magnitude = 1;
		}
		if ((i != Config.UP_CHAR) && (i != Config.DOWN_CHAR) && (i != Config.LEFT_CHAR) && (i != Config.RIGHT_CHAR)) {// if
																														// not
																														// correct
																														// value
			int[] calcDelta = { 0, 0 };
			return calcDelta;
		}
		if (i == Config.UP_CHAR) {
			y = -1;
			x = 0;
		}
		if (i == Config.DOWN_CHAR) {
			y = 1;
			x = 0;
		}
		if (i == Config.LEFT_CHAR) {
			y = 0;
			x = -1;
		}
		if (i == Config.RIGHT_CHAR) {
			y = 0;
			x = 1;
		} // The 4 if statement change direction as per the input
		int calcDelta[] = { y * magnitude, x * magnitude };// stores direction and magnitude in
															// calcdelta
		return calcDelta;
	}

	/**
	 * This method checks that moving from one position to another position is a
	 * valid move.
	 *
	 * To validate the move, the method should (in order) check: 1 - that pos is
	 * valid. 2 - that the character at pos in board is in the valid array. 3 - that
	 * the delta is valid. 4 - that the new position is valid and not a wall
	 * character. 5 - that the new position is not a box character For what makes
	 * each test invalid, see the return details below.
	 *
	 * @param board The current board.
	 * @param pos   The position to move from. A length 2 array, where index 0 is
	 *              the row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @param valid A character array containing the valid characters for the cell
	 *              at pos.
	 * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2,
	 *         or not on the board. -2 : if the character at pos is not valid (not
	 *         in the valid array). -3 : if delta is null or not length 2. -4 : if
	 *         the new position is off the board or a wall character -5 : if the new
	 *         position is a box character
	 */
	public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {// Implements checkDelta
		// first test
		// Checks if Valid
		if (pos == null) {
			return -1;
		}
		if (!(pos.length == 2)) {
			return -1;
		}
		int posR = pos[0];
		int posC = pos[1];
		if ((posR > board.length - 1) || (posR < 0)) {
			return -1;
		}
		if ((posC > board[posR].length - 1) || (posC < 0)) {
			return -1;
		}
		// Test 2
		// The character at pos is valid
		boolean isVa = false;
		char posCh = board[posR][posC];
		for (int i = 0; i < valid.length; i++) {
			if (valid[i] == posCh) {
				isVa = true;
			}
		}
		if (!isVa) {
			return -2;
		}

		// TEST 3
		// checks if delta is valid
		if (delta == null) {
			return -3;
		}
		if (delta.length != 2) {
			return -3;
		}
		// TEST 4
		// checks if position is valid
		int[] newPos = new int[2];
		newPos[0] = pos[0] + delta[0];
		newPos[1] = pos[1] + delta[1];
		int newPosR = newPos[0];
		int newPosC = newPos[1];
		if ((newPosR > board.length - 1) || (newPosR < 0)) {
			return -4;
		}
		if ((newPosC > board[newPosR].length - 1) || (newPosC < 0)) {
			return -4;
		}

		if (board[newPosR][newPosC] == Config.WALL_CHAR)// checks if not a wall character
		{
			return -4;
		}
		// TEST 5
		// checks if new position is not a box char
		if ((board[newPos[0]][newPos[1]] == Config.BOX_CHAR) || (board[newPos[0]][newPos[1]]) == Config.BOX_GOAL_CHAR) {
			return -5;
		}
		return 1;
	}

	/**
	 * Changes a character on the board to one of two characters (opt1 or opt2),
	 * depending on the value of the cell.
	 *
	 * Check the cell at position pos. If the character is val, change it to opt1.
	 * Otherwise, change it to opt2.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param val   The value to check for in the board.
	 * @param opt1  The character to change to if the value is val.
	 * @param opt2  The character to change to if the value is not val.
	 */
	public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {// Implements
																								// togglePos
		if (board[pos[0]][pos[1]] == val) {// Checks if it is equal to the character and changes accordingly
			board[pos[0]][pos[1]] = opt1;
		} else {
			board[pos[0]][pos[1]] = opt2;
		}

	}

	/**
	 * Moves a box on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid. Recall
	 * that there are 2 characters that can represent a box. Step 2: Use your
	 * togglePos method to correctly change the character at the new position to the
	 * appropriate box character. Step 3: Again use your togglePos method to
	 * correctly change the character at pos to the the appropriate character
	 * without a box.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return The return value of checkDelta if less than 1. Otherwise 1.
	 */
	public static int shiftBox(char[][] board, int[] pos, int[] delta) {
		char[] valid = { Config.BOX_CHAR, Config.BOX_GOAL_CHAR };
		int data = checkDelta(board, pos, delta, valid);// checks if the move is valid
		if (data != 1) {
			return data;
		}
		int[] newPos = new int[2];
		newPos[0] = pos[0] + delta[0];// new position after checking delta
		newPos[1] = pos[1] + delta[1];
		togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR);// Changes the new position to
																							// the appropriate char
		togglePos(board, pos, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR);// Changes the old position to the
																					// appropriate char
		return 1;

	}

	/**
	 * Processes a move of the worker step-by-step.
	 *
	 * Go through the delta step-by-step, calling doMove for each step. That is, if
	 * the delta is {0, -3}, your method should call doMove three times with an
	 * argument of {0, -1} for the delta parameter of doMove. Or, if the delta is
	 * {6, 0}, it would call the doMove six times with an argument of {1, 0} for the
	 * delta parameter of the doMove method.
	 *
	 * During the processing of the move, if ever a call to doMove returns a value
	 * less than 1, your method should stop processing and return that value.
	 *
	 * Note: You can assume that one of the cells of delta will be 0.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return If both of the cells of delta are 0, return 0. If the call to doMove
	 *         returns a value less than 1, return that value. Otherwise, return 1.
	 */
	public static int processMove(char[][] board, int[] pos, int[] delta) {// Implements processMove
		if (delta[0] == 0 && delta[1] == 0) {// if the delta entered is full of zeros it will skip the process and
												// return 0
			return 0;
		}
		if (delta[0] == 0) {
			int[] delt = { delta[0], delta[1] };
			for (int i = 0; i < Math.abs(delta[1]); i++) {// loops until the magnitude of the delta value is reached
				delt[1] = delta[1] / Math.abs(delta[1]);// makes the a new array equal to either 1 or -1
				int val = doMove(board, pos, delt);// if the value of doMove is less than 1 it will return that value
				if (val < 1) {
					return val;
				}
			}
		} else if (delta[1] == 0) {
			int[] delt = { delta[0], delta[1] };
			for (int i = 0; i < Math.abs(delta[0]); i++) {// loops until the magnitude of the delta value is reached
				delt[0] = delta[0] / Math.abs(delta[0]);// makes the a new array equal to either 1 or -1
				int val = doMove(board, pos, delt);
				if (val < 1) {// if the value of doMove is less than 1 it will return that value
					return val;
				}
			}
		}

		return 1;
	}

	/**
	 * Moves the worker on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid. Recall
	 * that there are 2 characters that can represent the worker. Step 2: If
	 * checkDelta returns -5, use your shiftBox method to move the box by delta
	 * before moving the worker. Step 3: Use your togglePos method to correctly
	 * change the character at the new position to the appropriate worker character.
	 * Step 4: Again use your togglePos method to correctly change the character at
	 * pos to the the appropriate character without a worker. Step 5: Update the
	 * position of the worker in pos.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return If checkDelta returns a value less than 1 that is not -5, return that
	 *         value. If checkDelta returns -5 and shiftBox returns a value less
	 *         than 0, return 0. Otherwise, return 1.
	 */
	public static int doMove(char[][] board, int[] pos, int[] delta) {
		char[] valid = { Config.WORKER_CHAR, Config.WORK_GOAL_CHAR };
		int val = checkDelta(board, pos, delta, valid);// checks if the delta is valid
		if (val != -5 && val < 1) {
			return val;
		}
		int[] newPos = new int[] { pos[0] + delta[0], pos[1] + delta[1] };
		if (val == -5 && shiftBox(board, newPos, delta) < 1) {// moves by delta
			return 0;
		}
		togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);// Changes the new
																								// position to the
																								// apporopriate char
		togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);// Changes the old position to
																							// the apporopriate char
		pos[0] = newPos[0];// updates the position of the worker
		pos[1] = newPos[1];

		return 1;
	}

	/**
	 * Checks all the cells in board and ensures that there are no goals that are
	 * not covered by boxes.
	 *
	 * @param board The current board.
	 * @return true if all the goals are covered by boxes. Otherwise, false.
	 */
	public static boolean checkWin(char[][] board) {// Implements Checkwin
		for (int j = 0; j < board.length; j++) {
			for (int i = 0; i < board[j].length; i++) {
				if (board[j][i] == Config.BOX_CHAR) {
					return false;
				}
			}
		} // runs every element and checks for a box
		return true;
	}

	/**
	 * This is the main method for the Sokoban game. It consists of the main game
	 * and play again loops with calls to the various supporting methods. The
	 * details of the main method for each milestone can be found in the BP1 -
	 * Sokoban write-up on the CS 200 webpage:
	 * https://cs200-www.cs.wisc.edu/wp/programs/
	 *
	 * For all milestones, you will need to create a Scanner object to read from
	 * System.in that you will pass to the helper methods.
	 *
	 * For milestone 3, you will need to create a Random object using Config.SEED as
	 * the seed. This object should be create at the beginning of the method,
	 * outside of any loops.
	 *
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		Random rand = new Random(Config.SEED);
		System.out.println("Welcome to Sokoban!");
		boolean keep = true;
		Scanner sc = new Scanner(System.in);
		while (keep) {
			int maxLvl = Config.LEVELS.length - 1;// -1 cause we include 0
			String prompt = "Choose a level between 0 and " + maxLvl + ": ";
			int lavel = promptInt(sc, prompt, 0, maxLvl);// calls promtint to get the int value
			if (lavel == -1) {// if the value of lavel is -1 it will choose a random level
				lavel = rand.nextInt(Config.LEVELS.length);
				System.out.println("Sokoban Level " + lavel);
			}
			int lvl = checkLevel(lavel, Config.LEVELS, Config.GOALS);// Checks if level is valid
			if (lvl == 1) {
			} else {// Based on the return error ouptput the correct statement
				System.out.println("Error loading level!");
				if (lvl == 0) {
					System.out.print("Level lvl must be 0 or greater!");
				}
				if (lvl == -1) {
					System.out.print("Error with Config.LEVELS");
				}
				if (lvl == -2) {
					System.out.print("Error with Config.GOALS");
				}
				if (lvl == -3) {
					System.out.print("Level lvl does not contain any boxes.");
				}
				if (lvl == -4) {
					System.out.print("Level lvl does not have the same number of boxes as goals.");
				}
				if (lvl == -5) {
					System.out.print("Level lvl has a goal location that is a wall.");
				}
				if (lvl == -6) {
					System.out.print("Level lvl has 0 or more than 1 worker(s).");
				}
				if (lvl == -7) {
					System.out.print("Level lvl contains duplicate goals.");
				} else {
					System.out.print("Unknown Error");
				}
				continue;
			}
			int[] ar = new int[2];
			char[][] board = initBoard(lavel, Config.LEVELS, Config.GOALS, ar);// initializes board
			boolean bud = false;
			int deltCount = 0;// counter
			while (bud == false) {
				String doo = null;
				printBoard(board);// prints board
				doo = promptString(sc, ": ");// calls and stores promptString
				if (doo.length() < 1) {
					continue;
				}
				if (doo.charAt(0) == Config.QUIT_CHAR) {// if the user uses the quit character it quits
					bud = true;
					break;
				} else {
					int[] delta = calcDelta(doo);// calls calcdelta and stores the value
					if (delta[0] == 0 && delta[1] == 0) {
					} else {
						processMove(board, ar, delta);
						if (delta[0] > 1 || delta[0] < -1) {// if the magnitude of the move is greater than 1 it counts
															// the magnitude of the move
							deltCount = deltCount + Math.abs(delta[0]);
						} else if (delta[1] > 1 || delta[1] < -1) {
							deltCount = deltCount + Math.abs(delta[1]);
						} else {
							deltCount++;
						} // adds to the counter
						if (checkWin(board)) {// checks if won
							System.out.println("Congratulations! You won in " + deltCount + " moves!");
							printBoard(board);
							break;
						}

					}

				}
			}

			char val = promptChar(sc, "Play again? (y/n) ");

			if (val == 'y') {// if the user inputs doesn't input y it will thank them for playing and end the
								// game
				continue;
			} else {
				break;
			}
		}
		System.out.println("Thanks for playing!");
	}
}
