
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
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class represents the information and actions for each decoration
 * 
 * @author Cole Johnstone
 *
 */
public class Decoration {
	private static PApplet processing; // It stores the reference to the
	// PApplet object which represents the display window of this application
	private PImage image;// It stores the reference to the image object of this object
	private float x; // represents the x-position of this object
	private float y; // represents the y-position of this object
	private boolean isDragging; // It indicated whether this object is being dragged or not
	private static int oldMouseX; // It stores the old x-position of the mouse
	private static int oldMouseY; // It stores the old y-position of the mouse

	/**
	 * This constructor sets up info for a new decoration with all parameters
	 * 
	 * @param processing    It stores the reference to the PApplet object which
	 *                      represents the display window of this application
	 * @param x             represents the x-position of this object
	 * @param y             represents the y-position of this object
	 * @param imageFileName stores name of of the file
	 */
	public Decoration(PApplet processing, float x, float y, String imageFileName) {
		Decoration.processing = processing; // processing: PApplet reference to the display window of the Fish Tank
											// application
		this.x = x; // x: x-position of this decoration object
		this.y = y;// y: y-position of this decoration object
		image = processing.loadImage(imageFileName);// imageFileName: filename of the image to be loaded for this object
	}

	/**
	 * When called returns the file stored in image
	 * 
	 * @return image
	 */
	public PImage getImage() {
		return image;
	}

	/**
	 * Returns the x-position of this decoration object
	 * 
	 * @return x position of object
	 */
	public float getPositionX() {
		return x;
	}

	/**
	 * Returns the y-position of this decoration object
	 * 
	 * @return y position of object
	 */
	public float getPositionY() {
		return y;
	}

	/**
	 * Checks whether this decoration object is being dragged returns true if the
	 * object is being dragged, false otherwise
	 * 
	 * @return boolean isDragging
	 */
	public boolean isDragging() {
		return isDragging;
	}

	/**
	 * 
	 * Starts dragging this decoration object Sets the oldMouseX and oldMouseY to
	 * the current position of the mouse
	 * 
	 */
	public void startDragging() {
		oldMouseX = processing.mouseX;// sets oldMouseX data field to the current x-position of the mouse
		oldMouseY = processing.mouseY;// sets oldMouseY data field to the current y-position of the mouse
		isDragging = true;// sets the isDragging data field of this object to true
	}

	/**
	 * Stops dragging this decoration object
	 * 
	 */
	public void stopDragging() {
		isDragging = false;
	}

	/**
	 * Checks whether the mouse is over this decoration object
	 * 
	 * @return boolean true if mouse is over the decoration object
	 */
	public boolean isMouseOver() {
		int decorWidth = this.getImage().width;
		int decorHeight = this.getImage().height;

		// checks if the mouse is over the provided decoration
		return processing.mouseX >= this.getPositionX() - decorWidth / 2
				&& processing.mouseX <= this.getPositionX() + decorWidth / 2
				&& processing.mouseY >= this.getPositionY() - decorHeight / 2
				&& processing.mouseY <= this.getPositionY() + decorHeight / 2;
	}

	/**
	 * Moves this decoration object with dx and dy
	 * 
	 * @param dx change in x movement
	 * @param dy change in y movement
	 */
	public void move(int dx, int dy) {
		x = x + dx;
		if (x < 0 || processing.mouseX < 0) {
			x = 0;
		} // making sure that both the object and mouse are within the application window
		if (x > processing.width || processing.mouseX > processing.width) {
			x = processing.width;
		}
		y = y + dy;
		if (processing.mouseY < 0 || y < 0) {
			y = 0;
		} // making sure that both the object and mouse are within the application window
		if (processing.mouseY > processing.height || y > processing.height) {
			y = processing.height;
		}
	}

	/**
	 * Draws this decoration object to the display window. This method sets also the
	 * position of this object to follow the moves of the mouse if it is being
	 * dragged
	 * 
	 */
	public void draw() {
		if (isDragging == true) {// object follows mouse while within the window if it should be dragged
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

		} else {// draws no matter what
			processing.image(image, x, y);
		}
	}
}
