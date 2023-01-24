
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P02 Fish Tank 1000
// Course:   CS 300 Fall 2020
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
//   _no__ Write-up states that pair programming is allowed for this assignment.
//   _no__ We have both read and understand the course Pair Programming Policy.
//   _no__ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources:  zybooks
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/*
 * This class makes a fish tank where you can add move and delete up to 8 fish
 * 
 * 
 */
public class FishTank {
	private static PApplet processing; // PApplet object that represents the graphic
										// interface of the JunglePark application
	private static PImage backgroundImage; // PImage object that represents the
											// background image
	private static Fish[] fishes; // perfect size array storing the different fish present
									// in the fish tank. These fish can be of different species.
	private static Random randGen; // Generator of random numbers

	public static void main(String[] args) {
		Utility.startApplication(); // starts the application
	}

	/**
	 * Defines the initial environment properties of this application
	 * 
	 * @param processingObj a reference to the graphic display window of this
	 *                      application
	 */
	public static void setup(PApplet processingObj) {
		processing = processingObj;
		fishes = new Fish[8];
		randGen = new Random();
		backgroundImage = processing.loadImage("Images/background.png");
		// Draw the background image at the center of the screen
	}

	/**
	 * Draws and updates the application display window. This callback method called
	 * in an infinite loop.
	 */
	public static void draw() {
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);
		// width [resp. height]: System variable of the processing library that stores
		// the width [resp. height] of the display window
		for (int i = 0; i < fishes.length; i++) {// if there is a fish the loop draws it
			if (fishes[i] != null) {
				fishes[i].draw();
			}
		}
	}

	/**
	 * Checks if the mouse is over a specific Fish whose reference is provided as
	 * input parameter
	 *
	 * @param Fish reference to a specific fish
	 * @return true if the mouse is over the specific Fish object (i.e. over the
	 *         image of the Fish), false otherwise
	 */
	public static boolean isMouseOver(Fish Fish) {
		PImage image = Fish.getImage();
		float XF = Fish.getPositionX();
		float YF = Fish.getPositionY();
		float width = image.width;
		float height = image.height;
		float XM = processing.mouseX;
		float YM = processing.mouseY;
		if (XM < XF + width / 2 && XM > XF - width / 2 && YM < YF + height / 2 && YM > YF - height / 2) {
			return true;// will only return true if the mouse is within the fish image area
		}
		return false;
	}

	/**
	 * Callback method called each time the user presses the mouse
	 */
	public static void mousePressed() {
		boolean[] array = new boolean[fishes.length];
		for (int i = 0; i < fishes.length; i++) {// runs through the fishes array and sends only
			if (fishes[i] != null) {// the spots with fish in it to check if the mouse is over it
				array[i] = isMouseOver(fishes[i]);
			} else {
				array[i] = false;
			}
		}
		int num = 0;
		int index = -1;
		for (int i = 0; i < array.length; i++) {// if the mouse is over a fish it adds true to
			if (array[i] == true) {// the index of the where the fish is stored in the array
				num += 1;
				index = i;
			}
		}
		if (num == 1) {
			fishes[index].setDragging(true);// if there is only 1 fish under the mouse starts
		} // dragging that fish
		if (num > 1) {
			for (int i = 0; i < fishes.length; i++) {// if more than 1 fish is under the mouse
				if (array[i] == true) {// makes the mouse drag the fish in the lower index
					fishes[index].setDragging(true);
					break;
				}
			}
		}
	}

	/**
	 * Callback method called each time the mouse is released
	 */
	public static void mouseReleased() {
		for (int i = 0; i < fishes.length; i++) {// makes the mouse stop dragging all fish
			if (fishes[i] != null) {
				fishes[i].setDragging(false);
			}
		}
	}

	/**
	 * Callback method called each time the user presses a key
	 */
	public static void keyPressed() {
		if (processing.key == 'F' || processing.key == 'f') {
			for (int i = 0; i < fishes.length; i++) {// finds next index with null
				if (fishes[i] == null) {
					float x1 = (float) randGen.nextInt(processing.width);// generates a random x-position of type
					// float within the width of the display window
					float y1 = (float) randGen.nextInt(processing.height); // generates a random y-position of type
					// float within the height of the display window
					fishes[i] = new Fish(processing, x1, y1);// gives random location for new fish
					draw();
					break;
				}
			}

		}
		if (processing.key == 'R' || processing.key == 'r') {// removes fish if mouse over fish
			boolean[] array = new boolean[fishes.length];// and r or R is pressed
			for (int i = 0; i < fishes.length; i++) {// runs through the fishes array and sends only
				if (fishes[i] != null) {// the spots with fish in it to check if the mouse is over it
					array[i] = isMouseOver(fishes[i]);
				} else {
					array[i] = false;
				}
			}
			int num = 0;
			int index = -1;
			for (int i = 0; i < array.length; i++) {// if the mouse is over a fish it adds true to
				if (array[i] == true) {// the index of the where the fish is stored in the array
					num += 1;
					index = i;
				}
			}
			if (num == 1) {// if there is only 1 fish under the mouse removes
				fishes[index] = null;// fish from fishes array
			}
			if (num > 1) {// if more than 1 fish is under the mouse
				for (int i = 0; i < fishes.length; i++) {
					if (array[i] == true) {// removes fish in lower index from fishes array
						fishes[index] = null;
						break;
					}
				}
			}
		}
	}

}
