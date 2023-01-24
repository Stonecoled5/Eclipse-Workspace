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
import java.util.Random;
/**
 * This lock box tests if the guess to open the lock box
 * is the same as the randomly generated password with given password length
 * @author Cole Johnstone
 *
 */
public class LockBox {
	protected static Random randGen;//initializes random variable
	private String password;//initializes a password
	private boolean isOpen;//initializes a boolean to see if the lockbox is open
	/**
	 * Generates new password with given passwordLength
	 * @param passwordLength
	 * @throws IllegalArgumentException for invalid password length
	 */
	public LockBox(int passwordLength) {
		//creates new random variable
		if(randGen == null) randGen = new Random();
		//checks password length
		if(passwordLength <= 0) throw new IllegalArgumentException("Invalid password length");
		//creates new password
		else {
			password = "";
			for(int i = 0; i < passwordLength; i++) {
				password += String.valueOf(randGen.nextInt(10));}
		}
	}
	/**
	 * Checks the provided guess against the stored password.
	 * Sets the instance field isOpen to true if the guess is correct.
	 * @param guess
	 */
	public void authenticate(String guess) {
		if(guess.equals(password)) isOpen = true;
	}
	/**
	 * Returns the stored password in plain text
	 * @return password
	 */
	public String hackMe() {
		return password;
	}
	/**
	 * Accessor for the isOpen field, to check whether your authentication was successful.
	 * @return isOpen
	 */
	public boolean isOpen() {
		return isOpen;
	}
	/**
	 * Resets the isOpen instance field to false.
	 */
	public void reset() {
		isOpen = false;
	}

}
