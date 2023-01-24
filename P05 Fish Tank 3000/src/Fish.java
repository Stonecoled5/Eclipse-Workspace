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
 * subclass of TankObject that deals with Fish behavior
 * 
 * @author Cole Johnstone
 *
 */
public class Fish extends TankObject{
	private int speed;
	private boolean isSwimming;
	/**
	 * creates a new fish object located at a random position of the
	 * display window
	 * @param speed
	 * @param fishImageFileName
	 */
	public Fish(int speed, String fishImageFileName){
		super(tank.randGen.nextInt(800),tank.randGen.nextInt(600),fishImageFileName);
		if(speed <= 0) throw new IllegalArgumentException("Warning: speed cannot be negative");
		this.speed = speed;
	}
	/**
	 * Makes new fish with speed 5 and orange picture
	 */
	public Fish(){
		this(5, "images" + File.separator + "orange.png");
	}
	/**
	 * Overrides the draw() method implemented in the parent class.
	 * This method sets the position of this fish to follow the
	 * mouse moves if it is dragging, calls its swim() method
	 * if it is swimming, and draws it to the display window.
	 * @see TankObject.draw()
	 */
	@Override
	public void draw() { 
		super.draw();
	    // if the fish is swimming, call its swim() method
	    if (isSwimming) {
	      swim();
	      tank.image(this.image, getX(), getY());
	    }
	    // draw the fish at its current position
	}
	/**
	 * Checks whether this fish is swimming
	 * @return boolean isSwimming
	 */
	public boolean isSwimming() { 
		return isSwimming;
	}
	/**
	 * Starts swimming this fish
	 */
	public void startSwimming() { 
		isSwimming = true;
	}
	/**
	 * Stops swimming this fish
	 */
	public void stopSwimming() {
		isSwimming = false;
	}
	/**
	 * Gets the speed of this fish
	 * @return int speed of fish
	 */
	public int speed() { 
		return speed;
	}
	/**
	 * Moves horizontally the fish one speed step from left to right.
	 */
	public void swim() { 
		setX((getX() + speed) % tank.width); 
	}

}
