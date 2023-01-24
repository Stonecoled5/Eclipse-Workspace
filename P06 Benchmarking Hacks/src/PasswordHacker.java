//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P06 Benchmark Hacks
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
 * Tries to hack into the lockbox
 * @author Cole Johnstone
 *
 */

import java.lang.Math;

public class PasswordHacker {
	private LockBox toPick;
	private int passwordLength;
	/**
	 * stores the provided password length for future reference and
	 * creates a new LockBox with a password of the same length
	 * @param passwordLength
	 */
	public PasswordHacker(int passwordLength) {
		//sends the parameter passwordlength to the accessible passwordlength
		this.passwordLength = passwordLength;
		//creates a new lockbox with a given passwordlength
		toPick = new LockBox(passwordLength);
	}
	/**
	 * Resets the lockbox, asks for the password and opens the lockbox
	 * Complexity: O( 1 )
	 */
	public void hack() {
		toPick.reset();
		//tests if the password from hackMe opens the lockbox
		toPick.authenticate(toPick.hackMe());
	}
	/**
	 * Resets the lockbox and guesses the password until the lockbox opens
	 * Complexity: O( n )
	 */
	public void bruteForce() {
		toPick.reset();
		//sets the highest count possible for iteration purposes
		int max_count = (int) (Math.pow(10, passwordLength));
		//runs through all possible combinations of a password length to open
		//the lockbox
		for(int count = 0; count < max_count; count++) {
			toPick.authenticate(generateGuess(count));
			if(toPick.isOpen()) break;
		}
		return;
	}
	/**
	 * returns the value of your guess count number as a guess itself
	 * @param count
	 * @return password guess
	 */
	public String generateGuess(int count) {
		String count_item = String.valueOf(count);
		//adjusts the sent guess if the count is longer than the password length
		if(count_item.length() > passwordLength) {
			return count_item.substring(
					count_item.length() - passwordLength);
		}
		//sends back guess with 0s on the left side of the count with password length
		//as its length
		return String.format("%0"+passwordLength+"d",count);
	}
}
