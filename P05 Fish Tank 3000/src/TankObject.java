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
import java.util.Random;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * A subclass of FishTank that initiates and deals with any TankObject
 * 
 * @author Cole Johnstone
 *
 */
public class TankObject extends FishTank implements TankListener{
	protected static FishTank tank; // PApplet object which represents
	// the display window
	protected PImage image; // image of this tank object
	private float x; // x-position of this tank in the display window
	private float y; // y-position of this tank in the display window
	private boolean isDragging; // indicates whether this tank object
	// is being dragged or not
	private static int oldMouseX; // old x-position of the mouse
	private static int oldMouseY; // old y-position of the mouse
	
	/**
	 * This constructor creates a new tank object located at a specific position of the display
	 * window. Sets TankObject so it must not be dragging
	 * 
	 * @param x
	 * @param y
	 * @param imageFileName
	 */
	public TankObject(float x, float y, String imageFileName) {
		this.x = x;
		this.y = y;
		image = tank.loadImage(imageFileName);
		this.isDragging = false;
	}
	/**
	 * Sets the PApplet graphic display window for all TankObjects
	 * 
	 * @param tank
	 */
	public static void setProcessing(FishTank tank) {
		TankObject.tank = tank;
	}
	/**
	 * Moves this tank object with dx and dy
	 * dx move to the x-position of this tank object
	 * dy move to the y-position of this tank object
	 * 
	 * @param dx
	 * @param dy
	 */
	public void move(int dx, int dy) {
		x += dx;
		if (x < 0 || tank.mouseX < 0) {
			x = 0;
		} // making sure that both the fish and mouse are within the application window
		if (x > tank.width || tank.mouseX > tank.width) {
			x = tank.width;
		} // adds dx move to the x-position of this fish
		y += dy;
		if (y < 0 || tank.mouseY < 0) {
			y = 0;
		} // making sure that both the fish and mouse are within the application window
		if (y > tank.height || tank.mouseY > tank.height) {
			y = tank.height;
		} // adds dy move to the y-position of this fish
		if(x >= 0 && tank.mouseX > 0 && x < tank.width && tank.mouseX < tank.width && 
				y > 0 && tank.mouseY > 0 && y < tank.height && tank.mouseY < tank.height) {
			x = tank.mouseX;
			y = tank.mouseY;
		}// if the mouse is within the window, object will follow mouse
	}
	/**
	 * Returns the x-position of this tank object
	 * @return x position of object
	 */
	public float getX() {
		return x;
	}
	/**
	 * Returns the y-position of this tank object
	 * @return y position of object
	 */
	public float getY() {
		return y;
	}
	/**
	 * Sets the x-position of this object
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * Sets the y-position of this object
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * Returns the image of this tank object
	 * @return image of object
	 */
	public PImage getImage() {
		return image;
	}
	/**
	 * Getter of the isDragging field.
	 * returns true if this object is being dragged, false otherwise
	 * @return boolean isDragging
	 */
	public boolean isDragging() {
		return isDragging;
	}
	
	/**
	 * Starts dragging this tank object
	 */
	public void startDragging() {
		oldMouseX = tank.mouseX;
	    oldMouseY = tank.mouseY;
	    this.isDragging = true;
	}
	/**
	 * Stops dragging this tank object
	 */
	public void stopDragging() {
		this.isDragging = false;
	}
	/**
	 * Draws this decoration object to the display window.
	 * @see FishTank.draw()
	 */
	@Override
	public void draw() {
		// if this object is dragging, set its position to follow the mouse moves
	    if (this.isDragging) {
	    	//if the mouse is within the display window have the object follow the mouse
	 		if (tank.mouseX <= tank.width && tank.mouseX >= 0
	 			&& tank.mouseY <= tank.height && tank.mouseY >= 0) {
	 			move(tank.mouseX - oldMouseX, tank.mouseY - oldMouseY);
	 			tank.image(image, tank.mouseX, tank.mouseY);
	 			oldMouseY = tank.mouseY;
	 			oldMouseX = tank.mouseX;
	 		}
	 		//if the mouse is left or right of the display window only have object follow mouseY
	    	if (tank.mouseX < 0 || tank.mouseX > tank.width && tank.mouseY >= 0 || tank.mouseY <= tank.height) {
	 			move(0, tank.mouseY - oldMouseY);
	 			tank.image(image, x, y);
	 			oldMouseY = tank.mouseY;
	 		}
	    	//if the mouse is above or below the display window only have object follow mouseX
	 		if (tank.mouseY < 0 || tank.mouseY > tank.height && tank.mouseX >= 0 || tank.mouseX <= tank.height) {
	 			move(tank.mouseX - oldMouseX, 0);
	 			tank.image(image, x, y);
	 			oldMouseX = tank.mouseX;
	 		}
	 		else {
	 			tank.image(image, x, y);
	 		}
	    }
		else {
			tank.image(image, x, y);
		}
	}
	/**
	 * called each time the mouse is Pressed
	 * @see FishTank.mousePressed()
	 */
	@Override
	public void mousePressed() {
		startDragging();
	}
	/**
	 * called each time the mouse is Released
	 * @see FishTank.mouseReleased()
	 */
	@Override
	public void mouseReleased() {
		stopDragging();
	}
	/**
	 * checks whether the mouse is over this Tank GUI
	 * return true if the mouse is over this tank GUI object, false otherwise
	 * @return boolean isMouseOver
	 * @see FishTank.isMouseOver()
	 */
	@Override
	public boolean isMouseOver() {
		 // checks if the mouse is over this object
	    return tank.mouseX >= x - image.width / 2 && tank.mouseX <= x + image.width / 2
	        && tank.mouseY >= y - image.height / 2 && tank.mouseY <= y + image.height / 2;
	}
}
