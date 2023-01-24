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
 * interface class to let subclasses override 
 * callback methods in superclass
 * 
 * @author Cole Johnstone
 *
 */
public interface TankListener {
	/**
	 * draws this tank object to the display window
	 * @see FishTank.draw()
	 */
	public void draw();
	// called each time the mouse is Pressed
	public void mousePressed();
	// called each time the mouse is Released
	public void mouseReleased();
	// checks whether the mouse is over this Tank GUI
	// return true if the mouse is over this tank GUI object, false otherwise
	public boolean isMouseOver();
	
}
