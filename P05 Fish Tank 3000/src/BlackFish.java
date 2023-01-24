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
 * subclass of Fish that deals with Black Fish behavior
 * @author Cole Johnstone
 *
 */
public class BlackFish extends Fish  {
	private TankObject source;
	private TankObject destination;
	/**
	 * constructor that initiates a black fish
	 * @param source
	 * @param destination
	 */
	public BlackFish(TankObject source, TankObject destination) {
		super(2, "images" + File.separator + "black.png");
		this.source = source;
		this.destination = destination;
	}
	/**
	 * makes one speed move towards destination
	 */
	public void moveTowardsDestination() { 
		float dx = destination.getX() - getX();
		float dy = destination.getY() - getY();
		int d = (int)Math.sqrt(dx*dx + dy*dy);
		setX(getX() + speed()*(destination.getX() - getX()) / d);
		setY(getY() + speed()*(destination.getY() - getY()) / d);
	}
	/**
	 * returns true if this black fish is over another tank object, and false otherwise
	 * @param other
	 * @return boolean isOver
	 */
	public boolean isOver(TankObject other) { 
		if(getX() + image.width/2 <= other.getX() - other.image.width/2 || 
		getX() - image.width/2 >= other.getX() + other.image.width/2 ||
		getY() + image.height/2 <= other.getY() - other.image.height/2 ||
		getY() - image.height/2 >= other.getY() + other.image.height/2) return false;
		else return true;
	}
	/**
	 * sets swim behavior for black fish
	 * @see Fish.swim()
	 */
	@Override
	public void swim() {
		TankObject middle = null;
		moveTowardsDestination();
		if(isOver(destination)) {
			middle = source;
			source = destination; 
			destination = middle;}
	// move the fish towards its destination
	// if destination is reached (meaning this fish is over its destination,
	// switch source and destination
	}
	
	
}
