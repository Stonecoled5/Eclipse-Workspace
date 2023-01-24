
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Fish Tank 2000
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
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents the data type for fish objects which will be created
 * and used in the FishTank application
 * 
 * @author Cole Johnstone
 */
public class Fish {
	private static PApplet processing;// It stores the reference to the PApplet
	// object which represents the display window of this application
	private PImage image;// It stores the reference to the image object of this fish
	private float x;// It represents the x-position of this fish
	private float y;// It represents the y-position of this fish
	private int speed;// swimming speed of fish
	private boolean isDragging;// It indicated whether this fish is being dragged or not
	private boolean isSwimming;// It indicated whether this fish is swimming (moving) or not
	private static int oldMouseX;// It stores the old x-position of the mouse
	private static int oldMouseY;// It stores the old y-position of the mouse

	/**
	 * Creates a new fish object located at a specific (x, y) position of the
	 * display window
	 * 
	 * @param processing        It stores the reference to the PApplet object which
	 *                          represents the display window of this application
	 * @param x                 It represents the x-position of this fish
	 * @param y                 It represents the y-position of this fish
	 * @param speed             swimming speed of fish
	 * @param fishImageFileName stores name of of the fish image file
	 */
	public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
		Fish.processing = processing;
		this.x = x;
		this.y = y;
		this.speed = speed;
		image = processing.loadImage(fishImageFileName);
		processing.draw();

	}

	/**
	 * Creates a new fish object positioned at the center of the display window that
	 * has a swimming speed of 5
	 * 
	 * @param processing It stores the reference to the PApplet object which
	 *                   represents the display window of this application
	 */
	public Fish(PApplet processing) {
		Fish.processing = processing;
		image = processing.loadImage("images" + File.separator + "orange.png");
		speed = 5;
		x = processing.width / 2;
		y = processing.height / 2;
		processing.draw();
	}

	/**
	 * Returns the image of type PImage of this fish
	 * 
	 * @return image of fish
	 */
	public PImage getImage() {
		return image;
	}

	/**
	 * Returns the x-position of this fish in the display window
	 * 
	 * @return x position of fish
	 */
	public float getPositionX() {
		return x;
	}

	/**
	 * Returns the y-position of this fish in the display window
	 * 
	 * @return y position of fish
	 */
	public float getPositionY() {
		return y;
	}

	/**
	 * Moves this fish with dx and dy
	 * 
	 * @param dx change in x movement of fish
	 * @param dy change in y movement of fish
	 */
	public void move(int dx, int dy) {
		x = x + dx;
		if (x < 0 || processing.mouseX < 0) {
			x = 0;
		} // making sure that both the fish and mouse are within the application window
		if (x > processing.width || processing.mouseX > processing.width) {
			x = processing.width;
		} // adds dx move to the x-position of this fish
		y = y + dy;
		if (processing.mouseY < 0 || y < 0) {
			y = 0;
		} // making sure that both the fish and mouse are within the application window
		if (processing.mouseY > processing.height || y > processing.height) {
			y = processing.height;
		} // adds dy move to the y-position of this fish
	}

	/**
	 * Checks if the mouse is over a given fish whose reference is provided as input
	 * parameter
	 * 
	 * @param fish reference to a given fish object
	 * @return true if the mouse is over the given fish object (i.e. over the image
	 *         of the fish), false otherwise
	 */
	public boolean isMouseOver() {
		int fishWidth = this.getImage().width;
		int fishHeight = this.getImage().height;

		// checks if the mouse is over the provided fish
		return processing.mouseX >= this.getPositionX() - fishWidth / 2
				&& processing.mouseX <= this.getPositionX() + fishWidth / 2
				&& processing.mouseY >= this.getPositionY() - fishHeight / 2
				&& processing.mouseY <= this.getPositionY() + fishHeight / 2;
	}

	/**
	 * Checks whether this fish is being dragged
	 * 
	 * @return boolean isDragging instance field
	 */
	public boolean isDragging() {
		return isDragging;
	}

	/**
	 * Starts dragging this fish by setting isDragging to true and updates oldMouse
	 * positions
	 * 
	 */
	public void startDragging() {
		oldMouseX = processing.mouseX;
		oldMouseY = processing.mouseY;
		isDragging = true;
	}

	/**
	 * Stops dragging this fish by setting isDragging to false
	 * 
	 */
	public void stopDragging() {
		isDragging = false;
	}

	/**
	 * Draws this fish to the display window. This method sets also the position of
	 * this fish to follow the moves of the mouse if it is being dragged
	 * 
	 */
	public void draw() {
		// fish follows mouse while within the window if it should be dragged
		// or and the fish isSwimming
		if (isDragging == true || isDragging == true && isSwimming == true) {
			if (processing.mouseX < 0 || processing.mouseX > processing.width) {
				move(0, processing.mouseY - oldMouseY);
				processing.image(image, x, y);
				oldMouseY = processing.mouseY;
			}
			if (processing.mouseY < 0 || processing.mouseY > processing.height) {
				move(processing.mouseX - oldMouseX, 0);
				processing.image(image, x, y);
				oldMouseX = processing.mouseX;
			}
			if (processing.mouseX <= processing.width && processing.mouseX >= 0
					&& processing.mouseY <= processing.height && processing.mouseY >= 0) {
				move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
				processing.image(image, x, y);
				oldMouseY = processing.mouseY;
				oldMouseX = processing.mouseX;
			} else {
				processing.image(image, x, y);
			}

		} // draws the fish swimming
		else if (isSwimming == true) {
			swim();
			processing.image(image, x, y);
		} // draws no matter what
		else {
			processing.image(image, x, y);
		}
	}

	/**
	 * Starts swimming this fish
	 * 
	 */
	public void startSwimming() {
		isDragging = false;// 1. stops dragging the fish
		isSwimming = true;// 2. sets the isSwimming instance field to true
	}

	/**
	 * Stops swimming this fish
	 * 
	 */
	public void stopSwimming() {
		isSwimming = false;// Sets the isSwimming instance field of this fish to false
	}

	/**
	 * Moves horizontally the fish one speed step from left to right using modulo so
	 * fish goes around the tank
	 * 
	 */
	public void swim() {
		x = (x + speed) % processing.width;
	}
}