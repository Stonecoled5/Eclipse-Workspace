import java.io.File;

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
 * Subclass of Button and creates a yellow fish button and
 * makes a new yellow fish when pressed
 * @author Cole Johnstone
 *
 */
public class AddYellowFishButton extends Button implements TankListener{
	/**
	 * Makes the yellow fish button
	 * @param x
	 * @param y
	 */
	public AddYellowFishButton(float x, float y) {
		super("Add Yellow", x, y);
	}
	/**
	 * Creates a new yellow fish when the yellow fish button is pressed
	 * and adds it to the objects array list
	 * @see Button.mousePressed()
	 */
	@Override
	public void mousePressed() {
		tank.addObject(new Fish(2, "images" + File.separator + "yellow.png"));
	}
}
