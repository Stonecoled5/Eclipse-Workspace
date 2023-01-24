
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
import java.io.File;
/**
 * subclass of Fish that deals with blue fish behavior
 * @author Cole Johnstone
 *
 */
public class BlueFish extends Fish implements TankListener{
	/**
	 * constructor that makes a new blue fish
	 */
	public BlueFish(){
		super(2, "images" + File.separator + "blue.png");
	}
	/**
	 * makes the blue fish swim
	 * @see Fish.swim()
	 */
	@Override
	public void swim() {
		if (getX() - speed() <= 0) setX(tank.width);
		else setX(getX() - speed()); 
	}
}
