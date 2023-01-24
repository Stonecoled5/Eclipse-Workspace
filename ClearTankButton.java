//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Fish Tank 3000
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
 * Subclass of Button and clears all fish when the clear button
 * is pressed
 * @author Cole Johnstone
 *
 */
public class ClearTankButton extends Button implements TankListener{
	/**
	 * Makes the clear button
	 * @param x
	 * @param y
	 */
	public ClearTankButton(float x, float y) {
		super("clear", x, y);
	}
	/**
	 * Clears all fish in the fish tank when the button is pressed
	 * @see Button.mousePressed()
	 */
	@Override
	public void mousePressed() {
		tank.clear();
	}
}
